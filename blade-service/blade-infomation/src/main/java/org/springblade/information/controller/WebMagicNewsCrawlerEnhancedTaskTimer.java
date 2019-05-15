package org.springblade.information.controller;


import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.entity.News;
import org.springblade.common.utils.CalendarUtils;
import org.springblade.information.service.NewsService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author hanbin
 * 新闻爬取的爬虫 使用webMagic进行增强
 */

//@Component
    @Api(tags ="新闻爬取的爬虫（webmagic）")
public class WebMagicNewsCrawlerEnhancedTaskTimer extends BaseNewsCrawler {

    //@Autowired
    public WebMagicNewsCrawlerEnhancedTaskTimer(NewsService newsService){
        super.newsService = newsService;
    }

    /**
     * 定时器
     * 每个五秒钟执行一次 @Scheduled(cron = "0/5 * * * * *")  0 20 10 * * ?
     * 每天10点触发@Scheduled(cron = "0 0 10 * * *")
    */

  //  @Scheduled(cron = "0/10 * * * * *")
    @Override
    public void scheduled() {

        //调用父类 可增强
        super.scheduled();

    }

    @Override
    protected List<String> getPageUrlList(int type) {

        final List<String> urlList = new ArrayList<>();

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                List<Selectable> selectables = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[2]/div[2]/div/ul/li").nodes();

                for(Selectable selectable : selectables){
                    String link = selectable.xpath("//a/@href").toString();
                    String date = selectable.xpath("//span/text()").toString();
                    System.out.println("link is "+link + "    -    date is "+date);

                    Date currentLingDate = CalendarUtils.getCurrentDate();
                    if(StringUtils.isNotBlank(date) && StringUtils.isNotBlank(link)){
                        String currentLingStr = simpleDateFormat.format(currentLingDate);
                        //是今天的日期的新闻就加入到里面
                        if(Objects.equals(date,currentLingStr)){
                            urlList.add(link);
                        }
                    }
                }

            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl("http://news.cnpc.com.cn/hynews/gw/").thread(1);

        spider.run();

        System.out.println("結束了");

        spider.close();

        return urlList;
    }


    /**
     * todo 认真考虑改造一下 尝试只用一个spider
     * @param url
     * @param type
     * @param maxDate
     * @return
     */
    @Override
    protected News generateNews(String url, int type, final Date maxDate) {

        final News news = new News();

        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                //  origin -   //*[@id="newsmain-ej"]/div/div[1]/div[1]/div[6]/ul/li[1]/a/text()[2]
                //  content -  //*[@id="newsmain-ej"]/div/div[1]/div[1]/div[4]/div
                //  publishTime  -   //*[@id="newsmain-ej"]/div/div[1]/div[1]/div[3]/div[2]/a/text()[2]
                //  title  -   //*[@id="newsmain-ej"]/div/div[1]/div[1]/div[2]/h2/a
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                //这里页面曾经遇到过修改页面样式的数据 TODO 仔细确认

                String origin = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[6]/ul/li[1]/a/text()").toString();
                String content = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[4]/div/html()").toString();
                String publishTime = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[3]/div[2]/a/text()").toString();
                String title = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[2]/h2/a/text()").toString();

                System.out.println(origin + ":"+ content + ":"+ publishTime + ":" + title);

                //进行判断
                if(StringUtils.isNotBlank(origin) && StringUtils.isNotBlank(content) && StringUtils.isNotBlank(publishTime) && StringUtils.isNotBlank(title)){
                    Date publishDate = null;
                    try {
                        publishDate = simpleDateFormat.parse(publishTime);
                    } catch (ParseException e) {
                        return ;
                    }
                    if(!maxDate.before(publishDate)){
                        return ;
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

            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl(url).thread(1);

        spider.run();

        spider.close();

        if(news.getDetails() == null){
            return null;
        }

        return news;
    }

}
