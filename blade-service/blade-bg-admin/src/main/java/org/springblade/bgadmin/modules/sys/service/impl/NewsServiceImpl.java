package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.NewsDao;
import org.springblade.bgadmin.modules.sys.entity.NewsEntity;
import org.springblade.bgadmin.modules.sys.service.NewsService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NewsEntity> page = this.page(
                new Query<NewsEntity>(params).getPage(),
                new QueryWrapper<NewsEntity>()
        );

        return new PageUtils(page);
    }

}
