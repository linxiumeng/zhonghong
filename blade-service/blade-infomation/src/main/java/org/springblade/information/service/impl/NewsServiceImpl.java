package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springblade.common.entity.News;
import org.springblade.common.utils.RedisUtils;
import org.springblade.common.utils.SpringContextUtils;
import org.springblade.information.mapper.NewsDao;
import org.springblade.information.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author hanbin
 * 新闻service实现类
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {

    /**
     * 线程池自建 核心1个，最大1个，线程存在时间60s，linkedblockingqueue存储，线程不够用 运行者处理
     */
    Executor executor = new ThreadPoolExecutor(1,
            2,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Resource
    RedisUtils redisUtils;

    @Override
    public boolean increaseViews(Long id) {
        //启动新线程执行incre 操作
        executor.execute(new Runnable() {
            @Override
            public void run() {
                baseMapper.increaseViews(id);
            }
        });
        return true;
    }

    @Override
    public Date selectMaxDateByType(int type) {
        return baseMapper.selectMaxDateByType(type);
    }

    //  @Cacheable(value = "news", key = "#id", unless = "#result == null")
    @Override
    public News selectByIdFromCache(Long id) {
        return this.getById(id);
    }

    @Override
    public Page<News> selectPageFromCache(Page page) {

        NewsService service = (NewsService) SpringContextUtils.getBean(NewsService.class);

        QueryWrapper<News> entityWrapper = new QueryWrapper<>();
        entityWrapper.le("create_date", new Date());
        //设置倒叙
        entityWrapper.orderBy(true, false, "create_date");

        //  SqlHelper.fillWrapper(page, entityWrapper);

        List<String> ids = baseMapper.selectIdListByPage(page, entityWrapper);

        int count = 0;

        List<News> newsList = redisUtils.mget(ids);

        for (News news : newsList) {
            if (news == null) {
                news = service.selectByIdFromCache(Long.valueOf(ids.get(count)));
                if (news != null) {
                    newsList.set(count, news);
                }
            }
            count++;
        }

        page.setRecords(newsList);

        return page;

    }

    @Override
    public Page<News> selectPageNoCache(Page page) {
        QueryWrapper<News> entityWrapper = new QueryWrapper<>();
        entityWrapper.le("create_date", new Date());
        //设置倒叙
        entityWrapper.orderBy(true, false, "create_date");

        //  SqlHelper.fillWrapper(page, entityWrapper);

        page.setRecords(baseMapper.selectPage(page, entityWrapper).getRecords());

        return page;
    }


}
