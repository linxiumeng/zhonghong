package org.springblade.information.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springblade.common.entity.*;
import org.springblade.common.enums.GoodsAuditStatusEnum;
import org.springblade.common.enums.GoodsStatusEnum;
import org.springblade.common.form.LoginForm;
import org.springblade.common.form.RegisterForm;
import org.springblade.common.utils.R;
import org.springblade.common.utils.RedisUtils;
import org.springblade.information.feign.UserServiceFeign;
import org.springblade.information.service.AnnouncementService;
import org.springblade.information.service.BannerService;
import org.springblade.information.service.GoodsService;
import org.springblade.information.service.NewsService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServer;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest(properties = {"spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.druid.url=jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.druid.username=root",
        "spring.datasource.druid.password=123456",
        "spring.redis.port=7777"},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
public abstract class BaseServiceTest {

    private static final String USER_REGISTER_KEY = "market:user:register";
    /**
     * 获取factorybean
     */
    @Resource
    SqlSessionFactory factory;

    RedisServer redisServer;


    //-------- 注入一系列的service类 ------

    @Resource
    protected RedisUtils redisUtils;

    @Resource
    protected UserServiceFeign userService;

//    @Resource
 //   protected DemandService demandService;

 //   @Resource
 //   protected QuotationService quotationService;

  //  @Resource
 //   protected TokenService tokenService;

    @Resource
    protected NewsService newsService;

    @Resource
    protected GoodsService goodsService;

    @Resource
    protected AnnouncementService announcementService;

 //   @Resource
 //   protected PurchaseOrdersService purchaseOrdersService;

    @Resource
    protected BannerService bannerService;


    // ------- 设置一些列的初始化方法

    @Before
    public void setUp() throws Exception {

        // 初始化数据库
        initDataBase();

        // 启动redisserver
        initRedisServer();
    }

    private void initDataBase() throws Exception{

        over();

        URL url = Thread.currentThread().getContextClassLoader().getResource("sql");
        File file = new File(url.getFile());

        List<File> fileList = Arrays.asList(file.listFiles());

        SqlSession session = factory.openSession();
        Connection connection = session.getConnection();

        //创建语法
        Statement statement =connection.createStatement();

        for (File subFile : fileList) {
            if (subFile.getName().contains(".sql")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("sql/"+subFile.getName())));

                StringBuilder sb = new StringBuilder();
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    sb.append(temp);
                }

                //添加批量batch
                statement.addBatch(sb.toString());

            }
        }

        //执行批量batch
        statement.executeBatch();
        statement.clearBatch();
        // 关闭 statement ， 注：connection由sqlSession托管 不需要手动关闭
        statement.close();
        session.close();
    }

    private void initRedisServer() throws Exception{

        //    File redisStoreFile = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile());
        redisServer = new RedisServer(7777);
        redisServer.start();

    }

    @After
    public void over() throws Exception{

        SqlSession session = factory.openSession();
        Connection connection = session.getConnection();

        Statement statement = connection.createStatement();
        statement.execute("drop all OBJECTS");
        statement.close();
        session.close();

        if(redisServer != null){
            redisServer.stop();
        }
    }


    // ----------- 一系列公用的创建的方法 -----

    protected void createGoods(){

        redisUtils.set("a","a");
        System.out.println(redisUtils.get("a"));

        Goods goods = new Goods();
        goods.setGoodsName("a");
        goods.setGoodsStock("10000");
        goods.setUserId(1L);
        goods.setAuditStatus(GoodsAuditStatusEnum.DEFAULT);
        goods.setGoodsStatus(GoodsStatusEnum.DOWN);
        goodsService.save(goods);
    }

    /**
     * 创建需求单
     */
    protected void createDemand(){

        userRegister();

        createGoods();

        Demand demand = new Demand();
        demand.setCreatUserid("1");
        demand.setFName("aaa");
     //   demandService.save(demand);
    }

    /**
     * 注册用户
     */
    protected void userRegister(){
        String mobile = "123456";
        String code = "testCode";
        String key = String.format("%s_%s", USER_REGISTER_KEY, mobile);
        redisUtils.set(key, code);

        RegisterForm registerForm = new RegisterForm();
        registerForm.setCode(code);
        registerForm.setMobile(mobile);
        registerForm.setPassword("aaa");
        R r = register(registerForm);
    }

    public R register(RegisterForm form){



        return R.ok();

    }


    /**
     * 创建公告
     */
    protected void createAnnounce(){

        Announcement announcement = new Announcement();
        announcement.setBody("这是正文");
        announcement.setTitle("这是标题");

        announcementService.save(announcement);

    }

    /**
     * 创建banner
     */
    protected void createBanner(){

        Banner banner = new Banner();
        banner.setPath("这是banner路径");
        banner.setIsOpen(1);

        bannerService.save(banner);

    }
    /**
     * 创建新闻
     */
    protected void createNews(){

        News news = new News();
        news.setTitle("这是新闻标题");
        news.setEditor("hhb");
        news.setDetails("asfagdag");
        news.setOrigin("shit");
        news.setType(1);

        newsService.save(news);

    }

}
