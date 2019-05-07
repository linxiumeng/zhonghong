package org.springblade.information.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springblade.common.entity.ChatMessage;
import org.springblade.common.entity.ChatSession;
import org.springblade.common.utils.R;
import org.springblade.information.service.ChatMessageService;
import org.springblade.information.service.ChatSessionService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author hanbin
 * websocket实例类
 */
@ServerEndpoint(value = "/websocket")
@Component
public class FinePetroWebSocket {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<FinePetroWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 存储session的map  fromUid,toUid -> session
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 消息接收总页面
     * todo 这个需要看一下原型
     */
    private static Map<Integer, Session> userSessionParentMap = new ConcurrentHashMap<>();


    /**
     * 存储用户的uid
     */
    private Integer uid;

    /**
     * from的常量
     */
    private static final String FROM = "from";

    /**
     * to的常量
     */
    private static final String TO = "to";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);

        //获取session后面的字符串
        //这里前端要构造一个字符串
        // from=? & to=?
        String queryString = session.getQueryString();
        Integer[] paramArr = parseQueryString(queryString);
        if (paramArr == null) {
            return;
        }
        try {
            // 判断连接的形式 是只有 from 还是from to都有
            if (paramArr[1] != null) {
                //单个聊天窗口
                sessionMap.put(paramArr[0] + "," + paramArr[1], session);
                //总窗口负责收总消息
                userSessionParentMap.put(paramArr[0], session);
            }
            sendMessage(session, "聊天服务器连接成功");
        } catch (NumberFormatException | IOException e) {
            //这里抓一下异常
            e.printStackTrace();
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //session后面代表的连接
        String queryString = session.getQueryString();
        //获取要发送到的session
        Integer[] paramArr = parseQueryString(queryString);

        if (paramArr == null) {
            return;
        }

        //存储消息
        ChatMessage chatMessage = saveMessage(paramArr[0], paramArr[1], message);

        if (chatMessage != null) {
            //获取去的session
            Session toSession = sessionMap.get(paramArr[1] + "," + paramArr[0]);
            Session toParentSession = userSessionParentMap.get(paramArr[0]);

            //更新会话
            updateConversations(paramArr[0], paramArr[1]);

            try {
                //如果在线，直接发送
                if (toSession != null) {
                    sendMessage(toSession, JSON.toJSONString(chatMessage));
                }
                if (toParentSession != null) {
                    sendMessage(toParentSession, JSON.toJSONString(chatMessage));
                }

                ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean(ObjectMapper.class);
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(R.ok()));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean(ObjectMapper.class);
            try {
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(R.error()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    private Integer[] parseQueryString(String queryString) {

        Integer[] resultArr = new Integer[2];

        try {
            String[] tempArr = queryString.split("&");
            if (tempArr.length < 2) {
                return null;
            }
            for (String sub : tempArr) {
                String[] subTempArr = sub.split("=");
                String type = subTempArr[0];
                if (Objects.equals(type, TO)) {
                    resultArr[1] = Integer.valueOf(subTempArr[1]);
                } else if (Objects.equals(type, FROM)) {
                    resultArr[0] = Integer.valueOf(subTempArr[1]);
                }
            }

            //如果两者其中一个为空，则返回空
            if (resultArr[0] == null) {
                return null;
            }

            return resultArr;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 生成实体保存消息到数据库
     *
     * @param from
     * @param to
     * @param message
     * @return
     */
    public ChatMessage saveMessage(Integer from, Integer to, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setTo(to);
        chatMessage.setFrom(from);
        chatMessage.setContent(message);
        chatMessage.setTitle("聊天消息");
        ChatMessageService chatMessageService = (ChatMessageService) applicationContext.getBean(ChatMessageService.class);
        boolean flag = chatMessageService.save(chatMessage);
        if (flag) {
            chatMessage.setCreateDate(new Date());
            chatMessage.setUpdateDate(new Date());
            return chatMessage;
        }
        return null;
    }

    /**
     * 双向更新会话
     *
     * @param from 来
     * @param to   去
     */
    public void updateConversations(Integer from, Integer to) {
        ChatSessionService chatSessionService = applicationContext.getBean(ChatSessionService.class);


        // -----  自增主键的弊端 所以insertOrUpdate应对高并发 会出现 自增主键序列涨
        ChatSession fromSession = chatSessionService.getOne(new QueryWrapper<ChatSession>().eq("`from`", from).eq("`to`", to));
        ChatSession toSession = chatSessionService.getOne(new QueryWrapper<ChatSession>().eq("`from`", to).eq("`to`", from));

        if (fromSession == null) {
            fromSession = new ChatSession();
            fromSession.setFrom(from);
            fromSession.setTo(to);
            chatSessionService.insertOrUpdate(fromSession,false);
        } else {
            fromSession.setUpdateDate(new Date());
            chatSessionService.updateById(fromSession);
        }

        if (toSession == null) {
            toSession = new ChatSession();
            toSession.setId(null);
            toSession.setTo(from);
            toSession.setFrom(to);
            chatSessionService.insertOrUpdate(toSession, true);
        } else {
            toSession.setUpdateDate(new Date());
            chatSessionService.updateById(toSession);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinePetroWebSocket that = (FinePetroWebSocket) o;
        return Objects.equals(session, that.session) &&
                Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, uid);
    }
}
