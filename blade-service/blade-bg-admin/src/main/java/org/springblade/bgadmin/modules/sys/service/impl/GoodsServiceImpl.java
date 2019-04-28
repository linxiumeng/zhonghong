package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.GoodsDao;
import org.springblade.bgadmin.modules.sys.entity.GoodsEntity;
import org.springblade.bgadmin.modules.sys.service.GoodsService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsEntity> page = this.page(
                new Query<GoodsEntity>(params).getPage(),
                new QueryWrapper<GoodsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage listGoodsWithType(IPage page, Wrapper wrapper) {

        //wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectPageList(page,wrapper));
        return page;
    }


}
