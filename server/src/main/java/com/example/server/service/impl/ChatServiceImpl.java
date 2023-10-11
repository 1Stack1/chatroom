package com.example.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.bean.*;
import com.example.common.constant.OnlineUser;
import com.example.common.constant.TimeInterval;
import com.example.dao.dao.GroupDao;
import com.example.dao.dao.OrganizationDao;
import com.example.server.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.websocket.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.common.constant.RedisPrefix.*;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    GroupDao groupDao;
    @Autowired
    OrganizationDao organizationDao;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /*@Autowired
    HistoryRepository historyRepository;*/

    //  存储当前对象,将用户名和其对应的对话用键值对存储
    public static final Map<Integer, Session> sessionMap = OnlineUser.onlineUserMap;


    /**
     * 会话连接一建立就执行,即用户登陆成功就将该用户名和该对话以键值对存储
     *
     * @param session  前后端会话
     * @param userId 前后端会话的用户id
     */
    @Override
    public synchronized void onOpen(Session session, Integer userId) {
        log.info("登录用户名id====" + userId);
        sessionMap.put(userId,session);
    }

    /**
     * 会话结束时执行,将该用户和他的会话键值对从hashmap中删除
     *
     * @param userId 会话结束的用户的用户id
     */
    @Override
    public void onClose(Integer userId) {
        log.info("id为"+userId + "的用户退出登录");
        sessionMap.remove(userId);
    }

    /**
     * 前端发送请求时
     *
     * @param message 用户发送的Message类型的数据
     */
    @Override
    public void onMessage(String message, Integer userId) {
        History history =  JSON.parseObject(message,History.class);
        int newId = Integer.parseInt(stringRedisTemplate.opsForValue().get("id"));
        history.setId(newId);
        stringRedisTemplate.opsForValue().increment("id");
        //1.判断是单聊还是群聊
        //2.1.单聊
        if(history.getState() == 0){
            if(!sessionMap.containsKey(history.getToId())){
                //2.2.如果接收方不在线
                history.setState(0);
            }else{
                //2.3.如果接收方在线
                history.setState(1);
                //2.4.将信息发送给接收方
                sendUser(message, sessionMap.get(history.getToId()));
            }
            //2.5.history存到redis
            String key = SANGLE_CHAT + TimeInterval.getDifTime();
            stringRedisTemplate.opsForList().rightPush(key,
                    JSON.toJSONString(history));
            stringRedisTemplate.expire(key,4, TimeUnit.SECONDS);
        }else if(history.getState() == 2){//3.群聊
            //3.1.获取群里所有用户id,遍历查看是否在线
            List<Integer> filteredIds = organizationDao.findAllUserOfOrg(history.getToId()).
                                                        stream().filter((id) -> {
                return sessionMap.containsKey(id);
            }).collect(Collectors.toList());
            //3.2给在线的用户发送消息
            filteredIds.stream().forEach((id)->{
                sendUser(message,sessionMap.get(id));
            });
            List<String> strFIds = filteredIds.stream().map((id) -> {
                return String.valueOf(id);
            }).collect(Collectors.toList());
            //3.3将history存到redis
            String key = ORG_CHAT + TimeInterval.getDifTime();
            String hVal = StringUtils.join(strFIds, ',');
            stringRedisTemplate.opsForHash().put(key,JSON.toJSONString(history),hVal);
        }
    }

    /**
     * 发生错误时执行
     *
     * @param session
     * @param error
     */
    @Override
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 向某个指定的用户发送数据
     *
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
     * 向所有用户发送数据
     *
     * @param message 需要发送的数据,Json类型字符串
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
