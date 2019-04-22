package org.springblade.information.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springblade.common.entity.Goods;
import org.springblade.common.enums.GoodsAuditStatusEnum;
import org.springblade.common.enums.GoodsStatusEnum;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


public class GoodsControllerTest extends BaseControllerTest {

  /*  @Resource
    GoodsService goodsService;

    @Resource
    UserService userService;

    @Resource
    RedisUtils redisUtils;*/

  /*  private void createUser() {


        String mobile = "123456";
        String code = "testCode";
        String key = String.format("%s_%s", USER_REGISTER_KEY, mobile);
        redisUtils.set(key, code);

        RegisterForm registerForm = new RegisterForm();
        registerForm.setCode(code);
        registerForm.setMobile(mobile);
        registerForm.setPassword("aaa");
        userService.register(registerForm);

    }

    protected void createGoods() {

        Goods goods = new Goods();
        goods.setGoodsName("a");
        goods.setGoodsStock("1");
        goods.setUserId(1L);
        goods.setAuditStatus(GoodsAuditStatusEnum.DEFAULT);
        goods.setGoodsStatus(GoodsStatusEnum.DOWN);
        goodsService.insert(goods);
    }*/

    @Resource
    SqlSessionFactory sqlSessionFactory;

    private final static int EXPIRE = 3600 * 12;

    private String createTokenWithJDBCTemplates(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();

        //生成token
        String token = UUID.randomUUID().toString().replace("-", "");

        try {

            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

            System.out.println(token);

            //保存或更新用户token

            PreparedStatement statement = connection.prepareStatement("insert into tb_token(user_id,token,update_time,expire_time)values(?,?,?,?)");
            statement.setLong(1,1L);
            statement.setString(2,token);
            statement.setTimestamp(3,new Timestamp(new Date().getTime()));
            statement.setTimestamp(4,new Timestamp(expireTime.getTime()));
            statement.executeUpdate();
        }catch (Exception e){

        }
        return token;
    }

    @Test
    public void selectOne() throws Exception {

        userRegister();

        createGoods();



        String tokenString = createTokenWithJDBCTemplates();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/selectone").header("token",tokenString)
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.provider").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row").exists());
    }

    @Test
    public void selectpage() throws Exception {

        createGoods();

        //修改创建的商品的上架状态+审核状态
        Goods goods = new Goods();
        goods.setId(1L);
        goods.setGoodsStatus(GoodsStatusEnum.ON);
        goods.setAuditStatus(GoodsAuditStatusEnum.OK);
        goodsService.updateById(goods);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page",1);
        jsonObject.put("size",10);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/selectpage")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        //执行完成后的断言
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row.records[0].goodsName").value("a"));

    }

    @Test
    public void selectStatus()throws Exception {
        createGoods();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",1);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/selectStatus")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists());
    }

    private static final String USER_REGISTER_KEY = "market:user:register";

    @Test
    public void insert() throws Exception{
        userRegister();

        //验证码
        String mobile = "123456";
        String code = "testCode";
        String key = String.format("%s_%s", "", mobile);
        redisUtils.set(key, code);

        //测试传参
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile",123456);
        jsonObject.put("code","testCode");
        String tokenString = createTokenWithJDBCTemplates();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/insert").header("token",tokenString)
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row").exists());

    }

    @Test
    public void update() throws Exception{
        userRegister();

        String mobile = "123456";
        String code = "testCode";
        String key = String.format("%s_%s", USER_REGISTER_KEY, mobile);
        redisUtils.set(key, code);

        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("mobile",123456);
        jsonObject.put("code","testCode");
        String tokenString = createTokenWithJDBCTemplates();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/update").header("token",tokenString)
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row").exists());
    }

    @Test
    public void up() throws Exception{
        userRegister();

        //验证码
        String mobile = "123456";
        String code = "testCode";
        String key = String.format("%s_%s", USER_REGISTER_KEY, mobile);
        redisUtils.set(key, code);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","testCode");
        jsonObject.put("id",1);
        jsonObject.put("status",0);

        String tokenString = createTokenWithJDBCTemplates();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/changestatus").header("token",tokenString)
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row").exists());
    }

    @Test
    public void selectPage() throws Exception{
        createGoods();

        userRegister();

        //
        Goods goods = new Goods();
        /*goods.setId(1L);
        goods.setGoodsStatus(GoodsStatusEnum.ON);
        goods.setAuditStatus(GoodsAuditStatusEnum.OK);*/
        goods.setUserId(1L);
        goodsService.updateById(goods);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page",1);
        jsonObject.put("size",10);

        String tokenString = createTokenWithJDBCTemplates();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/goods/provider_goods_page").header("token",tokenString)
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andDo(MockMvcResultHandlers.print());

        //执行完成后的断言
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row.records[0].goodsName").value("a"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.row.records[0].userId").value("1"));

    }
}