package org.springblade.information.controller;

import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.entity.News;
import org.springblade.common.enums.NewsTypeEnum;
import org.springblade.common.utils.CalendarUtils;
import org.springblade.common.utils.DateUtils;
import org.springblade.common.utils.HttpClientUtils;
import org.springblade.information.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.selector.Selectable;

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
@Component
@Api(tags = "新闻爬虫（java）")
public class JavaMatcherOilsNewsCrawlerTaskTimer {


    @Autowired
    NewsService newsService;

    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
     */
    @Scheduled(cron = "0 0/20 * * * *")
    public void scheduled() {
        saveBatchHotNews(getHotNews(getHotNewsUrl()));
        saveBatchPriceNews(getPriceNews(getPriceNewsUrl()));
        saveBatchReportNews(getReportNews(getReportNewsUrl()));
    }


    /**
     * 头条地址：http://news.cnpc.com.cn/toutiao/
     * 头条的xpath ： //*[@id="newsmain-ej"]/div/div[1]/div[2]/div[2]/div/ul
     *
     * @return
     */
    private List<String> getHotNewsUrl() {


        Date dbMaxDate = new Date(((Date)newsService.selectMaxDateByType(0)).getTime()+60000);

    //    Date dbMaxDate = DateUtils.stringToDate("2019-04-23","yyyy-MM-dd");

        List<String> detailUrlList = new ArrayList<>(5);

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                List<Selectable> selectables = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[2]/div[2]/div/ul/li").nodes();

                for (Selectable selectable : selectables) {
                    String date = (((PlainText) (selectable.xpath("//span/text()")))).getFirstSourceText();
                    Date date1 = DateUtils.stringToDate(date,"yyyy-MM-dd");
                    if (date1 != null && dbMaxDate.before(date1)) {
                        String link = selectable.xpath("//a/@href").toString();
                        if (StringUtils.isNotBlank(link)) {
                            detailUrlList.add(link);
                        }
                    }
                }
            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl("http://news.cnpc.com.cn/toutiao/").thread(1);
        spider.run();
        spider.close();
        return detailUrlList;
    }

    private List<News> getHotNews(List<String> url) {

        if(url.isEmpty()){
            return Collections.emptyList();
        }

        List<News> detailList = new ArrayList<>(5);

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                //这里页面曾经遇到过修改页面样式的数据 TODO 仔细确认

                String origin = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[6]/ul/li[1]/a[2]/text()").toString();
                String content = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[4]/div/html()").toString();
                String publishTime = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[3]/div[2]/a/text()").toString();
                String title = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[2]/h2/a/text()").toString();

                if (publishTime.contains("发表日期")) {
                    publishTime = publishTime.replaceAll("发表日期：", "").trim();

                }

                System.out.println(origin + ":" + content + ":" + publishTime + ":" + title);

                //进行判断
                if (StringUtils.isNotBlank(origin) && StringUtils.isNotBlank(content) && StringUtils.isNotBlank(publishTime) && StringUtils.isNotBlank(title)) {
                    Date publishDate = StringUtils.isBlank(publishTime) ? new Date() : DateUtils.stringToDate(publishTime, "yyyy-MM-dd HH:mm");

                    News news = new News();
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
                    news.setType(NewsTypeEnum.HOT.ordinal());
                    detailList.add(news);

                }


            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl(url.toArray(new String[url.size()])).thread(1);
        spider.run();
        System.out.println("結束了");
        spider.close();
        return detailList;
    }


    private List<String> getReportNewsUrl() {

        Date currentDate = new Date();
      //  Date currentDate = DateUtils.stringToDate("2019-05-24",DateUtils.DATE_PATTERN);
        List<String> detailUrlList = new ArrayList<>(10);

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                List<Selectable> selectables = page.getHtml().xpath("/html/body/div/div[9]/div[1]/div[2]/ul").nodes();

                for (Selectable selectable : selectables) {
                    List<Selectable> subSelectables = selectable.xpath("//li").nodes();
                    for (Selectable selectable1 : subSelectables) {
                        String date = (((PlainText) (selectable1.xpath("//span/text()")))).getFirstSourceText();
                        if (!DateUtils.format(currentDate, DateUtils.DATE_PATTERN).equalsIgnoreCase(date)) {
                            return;
                        } else {
                            String link = selectable1.xpath("//a/@href").toString();

                            if (StringUtils.isNotBlank(link)) {
                                detailUrlList.add("http://www.100ppi.com/forecast/" + link);
                            }
                        }
                    }
                }
            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl("http://www.100ppi.com/forecast/plist-11-45-1.html").thread(1);
        spider.run();
        spider.close();
        return detailUrlList;

    }

    private List<News> getReportNews(List<String> url) {

        if(url.isEmpty()){
            return Collections.emptyList();
        }

        Date dbMaxDate = new Date(((Date)newsService.selectMaxDateByType(NewsTypeEnum.REPORT.ordinal())).getTime()+60000);

      //  Date dbMaxDate = DateUtils.stringToDate("2019-04-23","yyyy-MM-dd");

        List<News> detailList = new ArrayList<>(10);

        for (String subUrl : url) {
            News news = null;

            String htmlContent = HttpClientUtils.doGet(subUrl);

            //增加了来源
            Matcher matcher = Pattern.compile("<div(?s).*?class=\"news-detail\">(?s).*?<h1>(.*?)</h1>(?s).*?<div(?s).*?class=\"nd-info\">(.*?)</div>(?s).*?overflow:hidden;width:588px;\">(.*?)</div").matcher(htmlContent);

            while (matcher.find()) {

                news = new News();
                String title = matcher.group(1).replaceAll("生意社","");
                String publishTime = matcher.group(2);
                if (StringUtils.isNotBlank(publishTime)) {
                    publishTime = publishTime.replaceAll("\n", "").
                            trim().replaceAll("http://www.100ppi.com", "").
                            replaceAll("&nbsp;", "").replaceAll("<font class=\"redk\">(?s)(.*?)</font>", "");
                }
                String content = matcher.group(3).replaceFirst("生意社","");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

                Date publishDate = null;

                try {
                    publishDate = simpleDateFormat.parse(publishTime);
                } catch (ParseException e) {
                    continue;
                }

                if(publishDate.before(dbMaxDate)){
                    continue;
                }

                //这里选择直接替换掉
                title = title.replace("--中国石油新闻中心", "");
                news.setTitle(title);
                news.setDetails(content);
                news.setTab("石油");
                news.setEditor("中国石油网");
                news.setOrigin("中国石油网");
                news.setCreateDate(publishDate);
                news.setType(NewsTypeEnum.REPORT.ordinal());
                detailList.add(news);

            }
        }

        return detailList;
    }

    private List<String> getPriceNewsUrl() {

         Date currentDate = new Date();

      //  Date currentDate = DateUtils.stringToDate("2019-05-29",DateUtils.DATE_PATTERN);

        List<String> detailUrlList = new ArrayList<>(10);

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                // /html/body/div/div[9]/div[1]/div[2]/ul[1]
                List<Selectable> selectables = page.getHtml().xpath("/html/body/div/div[9]/div[1]/div[2]/ul").nodes();

                for (Selectable selectable : selectables) {
                    List<Selectable> subSelectables = selectable.xpath("//li").nodes();
                    for (Selectable selectable1 : subSelectables) {
                        String date = (((PlainText) (selectable1.xpath("//span/text()")))).getFirstSourceText();
                        if (!DateUtils.format(currentDate, "yyyy-MM-dd").equalsIgnoreCase(date.split(" ")[0])) {
                            return;
                        } else {
                            String link = selectable1.xpath("//a[2]/@href").toString();
                            if (StringUtils.isNotBlank(link)) {
                                detailUrlList.add("http://www.100ppi.com/news/" + link);
                            }
                        }
                    }
                }
            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl("http://www.100ppi.com/news/list-11--45-1.html").thread(1);
        spider.run();
        spider.close();
        return detailUrlList;

    }

    private List<News> getPriceNews(List<String> url) {

        if(url.isEmpty()){
            return Collections.emptyList();
        }

        Date dbMaxDate = new Date(((Date)newsService.selectMaxDateByType(NewsTypeEnum.PRICE.ordinal())).getTime()+60000);

        //Date dbMaxDate = DateUtils.stringToDate("2019-04-23","yyyy-MM-dd");

        List<News> detailList = new ArrayList<>(5);

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                //这里页面曾经遇到过修改页面样式的数据 TODO 仔细确认

                String origin = "中国石油网";
                String content = page.getHtml().xpath("//*[@id=\"contendId\"]/div/div[2]/html()").toString();
                String publishTime = page.getHtml().xpath("//*[@id=\"contendId\"]/div/div[1]/text()").toString();
                String title = page.getHtml().xpath("//*[@id=\"contendId\"]/div/h1/text()").toString();

                if(StringUtils.isNotBlank(publishTime)){
                    publishTime = publishTime.replace("http://www.100ppi.com","").replaceAll("\\u00A0","").trim();
                }


                //进行判断
                if (StringUtils.isNotBlank(origin) && StringUtils.isNotBlank(content) && StringUtils.isNotBlank(publishTime) && StringUtils.isNotBlank(title)) {
                    Date publishDate = StringUtils.isBlank(publishTime) ? new Date() : DateUtils.stringToDate(publishTime, "yyyy年MM月dd日 HH:mm");

                    if(publishDate.before(dbMaxDate)){
                        return;
                    }

                    News news = new News();
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
                    news.setType(NewsTypeEnum.PRICE.ordinal());
                    detailList.add(news);

                }


            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl(url.toArray(new String[url.size()])).thread(1);

        spider.run();
        System.out.println("結束了");
        spider.close();
        return detailList;

    }


    private void saveBatchHotNews(List<News> hotNews) {
        if (hotNews.size() > 0) {
            newsService.saveBatch(hotNews);
        }
    }

    private void saveBatchPriceNews(List<News> priceNews) {
        if (priceNews.size() > 0) {
            newsService.saveBatch(priceNews);
        }
    }

    private void saveBatchReportNews(List<News> reportNews) {
        if (reportNews.size() > 0) {
            newsService.saveBatch(reportNews);
        }
    }

    /*public static void main(String[] args) {

        JavaMatcherOilsNewsCrawlerTaskTimer service = new JavaMatcherOilsNewsCrawlerTaskTimer();

        *//*List<String> hotUrl = service.getHotNewsUrl();

        System.out.println(hotUrl.toString());

        List<News> hotNews = service.getHotNews(hotUrl);*//*

        *//*List<String> priceUrl = service.getPriceNewsUrl();

        System.out.println(priceUrl);

        List<News> priceNews = service.getPriceNews(priceUrl);

        System.out.println(priceNews);*//*

        List<String> reportUrl = service.getReportNewsUrl();

        System.out.println(reportUrl);

        List<News> reportNews = service.getReportNews(reportUrl);

        System.out.println(reportNews);

    }*/


}
