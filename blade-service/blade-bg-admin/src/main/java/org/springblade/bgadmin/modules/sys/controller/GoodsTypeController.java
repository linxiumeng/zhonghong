package org.springblade.bgadmin.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springblade.bgadmin.modules.sys.entity.GoodsTypeEntity;
import org.springblade.bgadmin.modules.sys.service.GoodsTypeService;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("sys/goods_type")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //  @RequiresPermissions("sys:goods:list")
    public R list(@RequestBody GoodsTypeEntity goodsTypeEntity) {

        QueryWrapper<GoodsTypeEntity> wrapper = new QueryWrapper<>(goodsTypeEntity);
        return R.ok().put("result", goodsTypeService.list(wrapper));
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("sys:goods:save")
    public R save(@RequestBody GoodsTypeEntity goodsTypeEntity) {
        goodsTypeService.save(goodsTypeEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
  //  @RequiresPermissions("sys:goods:update")
    public R update(@RequestBody GoodsTypeEntity goodsTypeEntity) {
        //ValidatorUtils.validateEntity(goodsTypeEntity);
        goodsTypeService.updateById(goodsTypeEntity);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
  //  @RequiresPermissions("sys:goods:delete")
    public R delete(@RequestBody Integer[] ids) {
        goodsTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}