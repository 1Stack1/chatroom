package com.example.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.constant.OnlineUser;
import com.example.server.reposity.HistoryRepository;
import com.example.common.bean.Message;
import com.example.server.service.ChatService;
import com.example.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.websocket.*;
import java.io.IOException;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    RedisCache redisCache;

    @Autowired
    HistoryRepository historyRepository;

    //  存储当前对象,将用户名和其对应的对话用键值对存储
    public static final Map<String, Session> sessionMap = OnlineUser.onlineUserMap;


    /**
     * 会话连接一建立就执行,即用户登陆成功就将该用户名和该对话以键值对存储
     * @param session   前后端会话
     * @param username  前后端会话的用户名
     */
    @Override
    public synchronized void onOpen(Session session,String username) {
        System.out.println("当前用户名====" + username);
        if(!sessionMap.containsKey(username)){//如果用户当前未登录
            sessionMap.put(username, session);
            sendAllUsers(sendAllUsernames());
        }else{//向邮箱发送验证码

        }
    }

    /**
     * 会话结束时执行,将该用户和他的会话键值对从hashmap中删除
     * @param username 会话结束的用户的用户名
     */
    @Override
    public void onClose(String username) {
        System.out.println(username + "退出登录");
        sessionMap.remove(username);
        UserServiceImpl.set.remove(username);
        redisCache.deleteObject("login:"+username);
        sendAllUsers(sendAllUsernames());
    }

    /**
     * 前端发送请求时
     * @param message   用户发送的Message类型的数据
     */
    @Override
    public void onMessage(String message) {
        Message message1 = JSON.parseObject(message,Message.class);
        if("".equals(message1.getMsg())){//如果用户发送的数据是"",那么用户就是刚点击了其他用户,应该从用户的历史记录中找到和被点击用户的聊天记录


            String to=message1.getTo();
            String from=message1.getFrom();
            //获取两人之前的聊天记录
            List<Message> list1=historyRepository.findByFromAndTo(to,from);
            List<Message> list2=historyRepository.findByFromAndTo(from,to);
            list1.addAll(list2);

            //将聊天记录按时间排序
            list1.sort(new Comparator<Message>() {
                @Override
                public int compare(Message o1, Message o2) {
                    return o1.getTime().compareTo(o2.getTime());
                }
            });

            //向查看聊天记录的用户发送聊天记录
            for (Message s:list1){
                String message2=JSON.toJSONString(s);
                sendUser(message2,sessionMap.get(message1.getFrom()));
            }


        }else{//用户发送了数据
            if ("".equals(message1.getTo())) {//如果用户没有指定接收方,就默认在大厅中讲话
                sendAllUsers(JSON.toJSONString(message1));
            } else {//向指定用户发送数据
                Session sessionTo = sessionMap.get(message1.getTo());
                Session sessionFrom=sessionMap.get(message1.getFrom());
                sendUser(message, sessionTo);
                sendUser(message,sessionFrom);
            }
        }

    }

    /**
     * 发生错误时执行
     * @param session
     * @param error
     */
    @Override
    public void onError(Session session,Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 向某个指定的用户发送数据
     * @param message   需要发送的数据,Json类型字符串
     * @param sessionTo 需要被发送数据的对话
     */
    public void sendUser(String message, Session sessionTo) {
        try {
            sessionTo.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送当前在线的用户
     * @return 在线用户的用户名
     */
    public String sendAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (String username : sessionMap.keySet()) {
            usernames.add(username);
        }
        Message message=new Message();
        message.setUserNames(usernames);
        return JSON.toJSONString(message);
    }

    /**
     * 向所有用户发送数据
     * @param message   需要发送的数据,Json类型字符串
     */
    public void sendAllUsers(String message) {
        try {
            for (Session session : sessionMap.values()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
