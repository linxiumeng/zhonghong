package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.GoodsEntity;
import org.springblade.bgadmin.modules.sys.entity.GoodsWithTypeEntity;

import java.util.List;

/**
 * 商品表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface GoodsDao extends BaseMapper<GoodsEntity> {

    int selectPutOnGoodsByUserId(Integer id);

    List<GoodsWithTypeEntity> selectPageList(IPage iPage, @Param("ew") Wrapper wrapper);

}
