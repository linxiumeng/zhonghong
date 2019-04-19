package org.springblade.common.utils.crawler;/*
package io.finepetro.util.crawler;

import io.finepetro.util.CalendarUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;



@Data
public class WebMagicPageListCrawler implements PageProcessor{



    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);



    volatile List<String> urlList = Collections.emptyList();

    @Override
    public void process(Page page) {
        ////*[@id="newsmain-ej"]/div/div[1]/div[2]/div[2]/div/ul/li[1]
        ////*[@id="newsmain-ej"]/div/div[1]/div[2]/div[2]/div/ul/li/a/text()
        //这里筛选的是双列表
    //    List<String> itemList = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[2]/div[2]/div/ul/li/a/@href | //*[@id=\"newsmain-ej\"]/div/div[1]/div[2]/div[2]/div/ul/li/span/text()").all();

    //    List<Selectable> selectables = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[2]/div[2]/div/ul/li").nodes();



        //过滤设置成员列表
       // List<String> urlList = doFilterByDateAndGenerateList(itemList);


        List<Selectable> selectables = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[2]/div[2]/div/ul/li").nodes();

        for(Selectable selectable : selectables){
            String link = selectable.xpath("//a/@href").toString();
            String date = selectable.xpath("//span/text()").toString();
            System.out.println("link is "+link + "    -    date is "+date);
        }


    }



    private List<String> doFilterByDateAndGenerateList(List<String> itemList){
        List<String> urlList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int size = itemList.size();
        int halfSize = size / 2;
        for(int i = 0 ; i < halfSize ; i++){
            String dateStr = itemList.get(i + halfSize);
            String urlStr = itemList.get(i);

            Date currentLingDate = CalendarUtils.getCurrentDate();
            if(StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(urlStr)){
                String currentLingStr = simpleDateFormat.format(currentLingDate);
                //是今天的日期的新闻就加入到里面
                if(Objects.equals(dateStr,currentLingStr)){
                    urlList.add(urlStr);
                }
            }
        }

        return urlList;

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws InterruptedException {
        */
/*WebMagicPageListCrawler webMagicPageListCrawler = new WebMagicPageListCrawler();
        Spider spider = Spider.create(new WebMagicPageListCrawler()).addUrl("http://news.cnpc.com.cn/hynews/gw/").thread(1);
        spider.run();
        System.out.println("aa");
        spider.close();*//*


        Spider spider = Spider.create(new PageProcessor() {

            private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

            @Override
            public void process(Page page) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                String origin = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[6]/ul/li[1]/a/text()").toString();
                String content = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[4]/div/html()").toString();
                String publishTime = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[3]/div[2]/a/text()").toString();
                String title = page.getHtml().xpath("//*[@id=\"newsmain-ej\"]/div/div[1]/div[1]/div[2]/h2/a/text()").toString();

                System.out.println(origin + ":"+ content + ":"+ publishTime + ":" + title);



            }

            @Override
            public Site getSite() {
                return site;
            }
        }).addUrl("http://news.cnpc.com.cn/system/2019/03/19/001723329.shtml").thread(1);

        spider.run();

        System.out.println("結束了");

        spider.close();


        System.out.println("aasfsfafaf");
    }
}
*/
