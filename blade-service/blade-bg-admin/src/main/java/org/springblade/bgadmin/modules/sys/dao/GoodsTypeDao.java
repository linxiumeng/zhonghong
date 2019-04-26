package org.springblade.bgadmin.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.bgadmin.modules.sys.entity.GoodsTypeEntity;

/**
 * 商品表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface GoodsTypeDao extends BaseMapper<GoodsTypeEntity> {


    GoodsTypeEntity selectByGoodsTypeId(Integer id);


}
