package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.GoodsDao;
import io.finepetro.modules.sys.entity.GoodsEntity;
import io.finepetro.modules.sys.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsEntity> page = this.selectPage(
                new Query<GoodsEntity>(params).getPage(),
                new EntityWrapper<GoodsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Page listGoodsWithType(Page page, Wrapper wrapper) {

        wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectPageList(page,wrapper));
        return page;
    }


}
