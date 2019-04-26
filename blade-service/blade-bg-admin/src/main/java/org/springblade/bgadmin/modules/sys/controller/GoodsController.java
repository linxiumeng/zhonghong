package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.CheckBGListUtils;
import io.finepetro.modules.sys.entity.GoodsEntity;
import io.finepetro.modules.sys.enums.GoodsFormEnum;
import io.finepetro.modules.sys.form.GoodsForm;
import io.finepetro.modules.sys.service.GoodsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * 商品表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@RestController
@RequestMapping("sys/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions(value = {"sys:chanpinliebiao:list","sys:chanpinshenhe:list"},logical = Logical.OR)
    public R list(@RequestBody GoodsForm goodsForm) {
        Page page = new Page(goodsForm.getPage(),goodsForm.getSize());
        EntityWrapper entityWrapper = new EntityWrapper();
        CheckBGListUtils.check(entityWrapper,goodsForm,"create_date","goods_name","goods_company");

        GoodsFormEnum goodsFormEnum = goodsForm.getGoodsFormStatus();

        if(goodsForm.getGoodsType() != null){
            entityWrapper.eq("goods_type",goodsForm.getGoodsType());
        }

        if(goodsFormEnum != null){
            switch(goodsFormEnum){
                case NOT_YET_UP:{entityWrapper.eq("goods_status",0);entityWrapper.eq("audit_status",1);}break;
                case UP: {entityWrapper.eq("goods_status",1);entityWrapper.eq("audit_status",1);break;}
                case DOWN:{entityWrapper.eq("goods_status",2);entityWrapper.eq("audit_status",1);break;}
                case AGREE:entityWrapper.eq("audit_status",1);break;
                case DISAGREE:entityWrapper.eq("audit_status",2);break;
                case AGREEING:entityWrapper.eq("audit_status",0);break;
                case DISAGREE_AND_AGREEING:entityWrapper.in("audit_status",new Object[]{0,2});
                default:
            }
        }

        return R.ok().put("page", goodsService.listGoodsWithType(page,entityWrapper));
    }



    /**
     * 信息
     */
    @RequestMapping("/detail")
    @RequiresPermissions(value = {"sys:chanpinliebiao:detail"})
    public R info(@RequestBody GoodsForm goodsForm) {

        if(goodsForm.getGoodsId() == null){
            return R.error("参数错误");
        }

        GoodsEntity goods = goodsService.selectById(goodsForm.getGoodsId());

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:goods:save")
    public R save(@RequestBody GoodsEntity goods) {
            goodsService.insert(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:goods:update")
    public R update(@RequestBody GoodsEntity goods) {
        ValidatorUtils.validateEntity(goods);
            goodsService.updateAllColumnById(goods);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:goods:delete")
    public R delete(@RequestBody Integer[] ids) {
            goodsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    @PostMapping("review")
    @RequiresPermissions("sys:chanpinshenhe:verify")
    public R review(@RequestBody GoodsForm goodsForm){
        if(goodsForm.getGoodsId() == null || goodsForm.getAuditStatus() == null){
            return R.error("参数缺失");
        }

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(goodsForm.getGoodsId());
        goodsEntity.setAuditStatus(goodsForm.getAuditStatus());

        boolean flag = goodsService.updateById(goodsEntity);
        if(flag) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("put_on")
    @RequiresPermissions("sys:chanpinliebiao:updown")
    public R push(@RequestBody GoodsForm goodsForm){
        if(goodsForm.getGoodsId() == null || goodsForm.getGoodsStatus() == null){
            return R.error("参数缺失");
        }

        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(goodsForm.getGoodsId());
        goodsEntity.setGoodsStatus(goodsForm.getGoodsStatus());

        boolean flag = goodsService.updateById(goodsEntity);
        if(flag) {
            return R.ok();
        }
        return R.error();
    }

}
