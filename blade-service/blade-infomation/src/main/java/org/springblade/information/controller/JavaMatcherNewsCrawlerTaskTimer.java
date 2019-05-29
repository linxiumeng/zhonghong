package org.springblade.information.controller;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.entity.News;
import org.springblade.common.utils.HttpClientUtils;
import org.springblade.information.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanbin
 * 新闻爬取的爬虫 使用java的版本
 */
//@Component
@Api(tags ="新闻爬虫（java）")
public class JavaMatcherNewsCrawlerTaskTimer extends BaseNewsCrawler {

  //  @Autowired
    public JavaMatcherNewsCrawlerTaskTimer(NewsService newsService){
        super.newsService = newsService;
    }


    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     */
  //  @Scheduled(cron = "0 0 0/1 * * *")
    @Override
    public void scheduled() {

        //直接使用父类的方法
        //todo 可增强
        super.scheduled();
    }


    /**
     * 获取分页文章的所有url列表
     * <p>
     * 石油新闻不会太多 拿一页的数量就ok
     * 每天更新也就5-6篇
     *
     * @param type 0 - 国内 ， 1 - 国外
     * @return
     */
    @Override
    public List<String> getPageUrlList(int type) {

        //先设置容量20避免扩容
        ArrayList<String> urlList = new ArrayList<>(20);
        //先获取列表文章页的富文本
        String htmlContent = null;

        if (type == 0) {
            htmlContent = HttpClientUtils.doGet("http://news.cnpc.com.cn/hynews/gn/");
        } else if (type == 1) {
            htmlContent = HttpClientUtils.doGet("http://news.cnpc.com.cn/hynews/gw/");
        } else {
            return Collections.emptyList();
        }

        //正则匹配
        Matcher m = Pattern.compile("<li class=\"ejli\"><a href=\"(?s)(.*?)\" target=\"_blank\"(?s).*?<span class=\"fr as07\">(.*?)</span>").matcher(htmlContent);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //设置时区问题
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        String todayText = sdf.format(new Date());

        //循环遍历，添加到list容器中
        while (m.find()) {
            String url = m.group(1);
            String date = m.group(2);

            //是今天的新闻  并且 时间大于最新的文章时间 就放入url容器中
            if (todayText.equals(date)) {
                urlList.add(url);
            }
        }

        return urlList;
    }

    @Override
    public News generateNews(String url, int type, Date maxDate) {
        News news = null;

        String htmlContent = HttpClientUtils.doGet(url);

        //增加了来源
        Matcher matcher = Pattern.compile("<title>(.*?)</title>(?s).*?<!--function pub_date(?s).*?parse begin-->(?s)(.*?)<!--function: pub_date(?s).*?parse end(?s).*?0ms cost!(?s).*?-->(?s).*?<div class=\"as04\".*?style=\".*?display:block;.*?width:650px;\">(?s)(.*?)</div>(?s).*?<!--function.*?source_without_pub_date.*?parse begin-->(?s)(.*?)<!--function:.*?source_without_pub_date.*?parse end.*?0ms cost!.*?-->").matcher(htmlContent);

        while (matcher.find()) {

            news = new News();
            String title = matcher.group(1);
            title = title.replaceAll("(\r\n|\r|\n|\n\r)", "");
            String publishTime = matcher.group(2);
            publishTime = publishTime.replaceAll("(\r\n|\r|\n|\n\r)", "");
            String content = matcher.group(3);
            String origin = matcher.group(4);

            //对来源字符串进行替换 替换掉<a>标签
            int startIndex = origin.indexOf("_blank\">");
            if(startIndex > 0 ) {
                int endIndex = origin.indexOf("</a>");
                origin = origin.substring(startIndex, endIndex).replaceAll("_blank\">", "");
            }

            origin = origin.replaceAll("(\r\n|\r|\n|\n\r)", "");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date publishDate = null;

            try {
                publishDate = simpleDateFormat.parse(publishTime);
            } catch (ParseException e) {
                return null;
            }

            if(!maxDate.before(publishDate)){
                return null;
            }

            //如果是空字符串的话，就设置默认值
            if (StringUtils.isBlank(origin)) {
                origin = "中国石油新闻中心";
            }
            //这里选择直接替换掉
            title = title.replace("--中国石油新闻中心", "");
            news.setTitle(title);
            news.setDetails(content);
            news.setTab("石油");
            news.setEditor("中国石油网");
            news.setOrigin(origin);
            news.setCreateDate(publishDate);
            news.setType(type);
        }

        return news;
    }



}
