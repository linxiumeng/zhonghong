package org.springblade.information.controller;

import io.swagger.annotations.Api;
import org.springblade.common.entity.News;
import org.springblade.information.service.NewsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hanbin
 * 模板方法模式
 */
@Api(tags = "新闻业务类")
public abstract class BaseNewsCrawler {

    /**
     * 新闻业务类
     */
    protected NewsService newsService;

    /**
     * 获取文章url列表
     * @param type
     * @return
     */
    protected abstract List<String> getPageUrlList(int type);

    /**
     * 生成新闻类
     * @param url
     * @param type
     * @param maxDate
     * @return
     */
    protected abstract News generateNews(String url, int type, Date maxDate);

    /**
     * 爬取国内的新闻，并且插入
     *
     * @param type 0 - 国内 ， 1 - 国际
     */
    public void batchInsertNews(int type) {

        List<String> urlList = getPageUrlList(type);

        List<News> newsList = new ArrayList<>(20);

        //获取当前类型的最大时间 加他1分钟
        Date maxDate = new Date(((Date)newsService.selectMaxDateByType(type)).getTime()+60000);

        for (String singleNewsUrl : urlList) {
            //爬取内容生成新闻类
            News news = generateNews(singleNewsUrl,type,maxDate);
            if (news != null) {
                newsList.add(news);
            }
        }

        if (newsList.size() > 0) {
            newsService.saveBatch(newsList);
        }
    }

    /**
     * 定时器
     */
    protected void scheduled() {

        try {
            //国内的新闻
            batchInsertNews(0);
            //国际的新闻
            batchInsertNews(1);

        } catch (Exception e) {
            //这里抓一下异常
            e.printStackTrace();
        }
    }
}
