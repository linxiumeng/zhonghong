package org.springblade.information.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.annotation.HasPermission;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.Goods;
import org.springblade.common.entity.GoodsTypeEntity;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.GoodsStatusEnum;
import org.springblade.common.exception.RRException;
import org.springblade.common.form.GoodsCheckCodeForm;
import org.springblade.common.form.GoodsStatusForm;
import org.springblade.common.form.GoodsTypeForm;
import org.springblade.common.form.PageForm;
import org.springblade.common.utils.R;
import org.springblade.common.utils.SmsCheckUtils;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.common.validation.group.SelectDetailGroup;
import org.springblade.common.validation.group.UpdateGroup;
import org.springblade.information.feign.UserServiceFeign;
import org.springblade.information.service.GoodsService;
import org.springblade.information.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * @author linxiumeng
 * @author hanbin
 * @since 2019-02-13 15:23:01
 */
@Api(tags = "商品表操作控制器(TbGoods)", description = " * @author linxiumeng")
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Resource
    private UserServiceFeign userService;

    @Resource
    SmsCheckUtils smsCheckUtils;

    @Resource
    GoodsTypeService goodsTypeService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @ApiOperation(value = "查询单个商品表(TbGoods)数据")
    @PostMapping("selectone")
    @Login
    @HasPermission(needVerifyUser = true)
    public R selectOne(@RequestParam String id) {
        Goods row = this.goodsService.getGoodsFromCache(Long.valueOf(id));
        UserEntity userEntity = null;
        if (row != null) {
            Long userId = row.getUserId();
            if (userId != null) {
                userEntity = userService.getUserById(userId).getData();
            }
            if(row.getGoodsType() != null){
                row.setGoodsTypeEntity(goodsTypeService.getById(row.getGoodsType()));
            }
        }

        return R.ok().put("row", row).put("provider", userEntity);
    }

    @ApiOperation(value = "分页查询商品表(TbGoods)数据")
    @PostMapping("selectpage")
    public R selectpage(@RequestBody PageForm pageForm) {
        //针对于前端select框做了特殊处理
        if(pageForm.getType() != null && pageForm.getType() == -1){
            pageForm.setType(null);
        }
        return R.ok().put("row", goodsService.listGoodsByOnline(pageForm));
    }

    @ApiOperation(value = "查询商品状态")
    @PostMapping("selectStatus")
    @Login
    @HasPermission(needVerifyCredit = true)
    public R selectStatus(@RequestBody @Validated(SelectDetailGroup.class) Goods param) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("id", param.getId());
        Goods row = this.goodsService.getById(param.getId());
        if (row == null) {
            return R.error(601, "商品不存在");
        }
        if (row.getGoodsStatus() == GoodsStatusEnum.DOWN) {
            return R.error(602, "商品已下架");
        }
        if (row.getGoodsStatus() == GoodsStatusEnum.NOT_ON) {
            return R.error(603, "商品未审核");
        }
        return R.ok("商品状态正常");
    }

    /************************供应商*/
    @ApiOperation(value = "新增商品表(TbGoods)数据")
    @PostMapping("insert")
    @Login
    @HasPermission(needVerifyUser = true)
    public R insert(@RequestBody @Validated(InsertGroup.class) GoodsCheckCodeForm param, @LoginUser UserEntity user) {

        //修改校验方式 ， 避免重复代码
        if (!smsCheckUtils.check(user.getMobile(), param.getCode())) {
            return R.error("验证码错误");
        }

        //对于固定价格和浮动价格的参数校验
        if(param.getGoodsPrice() == null){
            if(param.getPricingManner() == null || param.getContractualValueDate() == null){
                throw new RRException("确定浮动价格的同时，计价日期和计价基准需要确定");
            }
            param.setGoodsPrice(0D);
        } else {
            param.setPricingManner(null);
            param.setContractualValueDate(null);
        }


        param.setUserId(user.getUserId());
        param.setGoodsCompany(user.getCompanyName());
        Boolean row = this.goodsService.save(param);
        return R.ok().put("row", row);
    }


    @ApiOperation(value = "编辑商品数据")
    @PostMapping("update")
    @Login
    @HasPermission(needVerifyUser = true)
    public R update(@RequestBody @Validated(UpdateGroup.class) GoodsCheckCodeForm param, @LoginUser UserEntity user) {

        if (!smsCheckUtils.check(user.getMobile(), param.getCode())) {
            return R.error("验证码错误");
        }
        //这里设置只能拥有者修改
        return R.ok().put("row", goodsService.updateGoodsByIdAndUserId(param, user.getUserId()));
    }


    @ApiOperation(value = "更改商品状态")
    @PostMapping("changestatus")
    @Login
    @HasPermission(needVerifyUser = true)
    public R up(@RequestBody GoodsStatusForm param, @LoginUser UserEntity user) {

        if (!smsCheckUtils.check(user.getMobile(), String.valueOf(param.getCode()))) {
            return R.error("验证码错误");
        }
        return R.ok().put("row", goodsService.updateGoodsStatusByIdAndUserId(param.getStatus(), param.getId(), user.getUserId()));
    }

    @ApiOperation(value = "分页查询供应商发布的商品表(TbGoods)数据")
    @PostMapping("provider_goods_page")
    @Login
    @HasPermission(needVerifyUser = true)
    public R selectPage(@RequestBody PageForm pageForm, @LoginUser UserEntity userEntity) {
        return R.ok().put("row", goodsService.listGoodsByUserId(pageForm, userEntity.getUserId()));
    }


    @PostMapping("typeList")
    @ApiOperation(value = "商品类别列表" )
    public R getGoodsDetail(@RequestBody GoodsTypeForm goodsTypeForm){
        QueryWrapper<GoodsTypeEntity> wrapper = new QueryWrapper<>();
        if(goodsTypeForm.getIsOpen() != null && goodsTypeForm.getIsOpen() != -1) {
            wrapper.eq("is_open", goodsTypeForm.getIsOpen());
        }
        wrapper.orderByDesc("create_date");
        return R.ok().put("result", goodsTypeService.list(wrapper));
    }




    @ApiOperation(value = "获取商品详细信息" )
    @GetMapping("/detail")
    public org.springblade.core.tool.api.R decrGoodsStock(@RequestParam("goodsId")Long goodsId){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        r.setData(goodsService.getById(goodsId));
        return r;
    }
    @ApiOperation(value = "减少商品库存" )
    @GetMapping("decr_goods_stock")
    public org.springblade.core.tool.api.R decrGoodsStock(@RequestParam("goodsId")Long goodsId,@RequestParam("count")Integer count){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        boolean flag = goodsService.decrGoodsStock(goodsId,count);
        r.setData(flag);
        return r;
    }
    @ApiOperation(value = "增加商品库存" )
    @GetMapping("incr_goods_stock")
    public org.springblade.core.tool.api.R incrGoodsStock(@RequestParam("goodsId")Long goodsId,@RequestParam("count")Integer count){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        boolean flag = goodsService.incrGoodsStock(goodsId,count);
        r.setData(flag);
        return r;
    }

    @GetMapping("batchGetListByIds")
    @ApiOperation(value = "批量获取商品列表" )
    public org.springblade.core.tool.api.R<Collection<GoodsTypeEntity>> batchGetTypeListByIds(@RequestParam("ids")Collection<Long> ids){
        org.springblade.core.tool.api.R r = org.springblade.core.tool.api.R.status(true);
        Collection<GoodsTypeEntity> goodsTypeEntities = null;
        if(ids.isEmpty()){
            goodsTypeEntities = Collections.EMPTY_LIST;
        }else{
            goodsTypeEntities = goodsTypeService.listByIds(ids);
        }
        r.setData(goodsTypeEntities);
        return r;
    }


}