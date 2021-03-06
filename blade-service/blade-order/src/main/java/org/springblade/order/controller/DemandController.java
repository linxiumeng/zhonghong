package org.springblade.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.annotation.HasPermission;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.Demand;
import org.springblade.common.entity.GoodsTypeEntity;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.DemandForm;
import org.springblade.common.form.PageForm;
import org.springblade.common.respond.DemandResp;
import org.springblade.common.utils.R;
import org.springblade.order.feign.GoodsServiceFeign;
import org.springblade.order.feign.UserServiceFeign;
import org.springblade.order.service.DemandService;
import org.springblade.order.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author linxiumeng
 * @since 2019-03-07 10:57:43
 */
@Api(tags = "(Demand)表操作控制器", description = " * @author linxiumeng")
@RestController
@RequestMapping("api/demand")
public class DemandController {
    private DemandService demandService;

    private QuotationService quotationService;

    @Resource
    UserServiceFeign userService;

    @Resource
    GoodsServiceFeign goodsService;

    @Autowired
    public DemandController(DemandService demandService, QuotationService quotationService) {
        this.demandService = demandService;
        this.quotationService = quotationService;
    }


    @PostMapping("creat")
    @Login
    @ApiOperation(value = "创建需求接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    @HasPermission(needVerifyUser = true)
    public R creatDemand(@RequestBody DemandForm param, @LoginUser UserEntity user) {
        param.setCreatUserid(user.getUserId().toString());
        demandService.save(param);
        return R.ok();
    }


    @PostMapping("queryList")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    @ApiOperation(value = "查看自己需求接口")
    public R listDemands(@RequestBody DemandForm demandForm, @LoginUser UserEntity user) {

        if(demandForm.getGoodsType() != null && demandForm.getGoodsType() == -1){
            demandForm.setGoodsType(null);
        }

        Page<DemandResp> demandRespPage = demandService.listOwnDemandPage(new Page(demandForm.getPage(),demandForm.getSize()),user.getUserId(),demandForm.getGoodsType(),demandForm.getStatus());

        //优化版
        //  demandRespPage = demandService.selectDemandListWithQuotationList(page,wrapper);

        return R.ok().put("result", demandRespPage);
    }

    @PostMapping("updateDemand")
    @Login
    @ApiOperation(value = "编辑自己需求接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    @HasPermission(needVerifyUser = true)
    public R updateDemand(@RequestBody DemandForm param, @LoginUser UserEntity user) {
        //更新自己的接口
        boolean update = demandService.updateDemandByMyself(param,user.getUserId());
        if (update) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("getbyid")
    @Login
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    @ApiOperation(value = "查看需求详情接口")
    public R getbyid(@RequestBody Demand param, @LoginUser UserEntity user) {
        R r = R.ok();
        Map<String,Object> map = demandService.getDemandInfoAndQuotationListInfo(param.getId().longValue(),user.getUserId());
        r.putAll(map);
        return r;
    }


    @PostMapping("can_quotate_list")
    @ApiOperation(value = "查看所有可报价的需求单")
    public R getCanQuotateList(@RequestBody DemandForm param) {

        if(param.getGoodsType() != null && param.getGoodsType() == -1){
            param.setGoodsType(null);
        }

        long startTime = System.currentTimeMillis();
        System.out.println();
        IPage resultPage = demandService.listCanQuotationDemand(new Page(param.getPage(),param.getSize()),null,param.getGoodsType());
        System.out.println("run can_quotate_list spend "+(System.currentTimeMillis() - startTime));
        return R.ok().put("result", resultPage);
    }


    @PostMapping("detail")
    @Login
    @ApiOperation(value = "查看需求详情接口:不需要验证和返回报价信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    public R getbyidNotCheckParam(@RequestBody Demand param, @LoginUser UserEntity user) {
        Demand demand = demandService.getById(param.getId());
        UserEntity userEntity = null;

        if(demand == null){
            return R.error("需求单不存在");
        }

        if (demand != null && demand.getCreatUserid() != null) {
        //    userEntity = userService.getUserById(Long.valueOf(demand.getCreatUserid())).getData();
            if(StringUtils.isNotBlank(demand.getCreatUserid())) {
                org.springblade.core.tool.api.R<UserEntity> r = userService.getUserById(Long.valueOf(demand.getCreatUserid()));
                if (r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS) {
                    userEntity = null;
                }else{
                    userEntity = r.getData();
                }
            }
            demand.setCreateUser(userEntity);
        }
        if(demand.getFType() != null){
            List<GoodsTypeEntity> goodsTypeEntities = (List<GoodsTypeEntity>)(goodsService.batchGetGoodsType(Arrays.asList(demand.getFType())).getData());
            if(!goodsTypeEntities.isEmpty()){
                demand.setGoodsTypeEntity(goodsTypeEntities.get(0));
            }
        }

        return R.ok().put("row", demand);
    }


}