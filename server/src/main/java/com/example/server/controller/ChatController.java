package com.example.server.controller;


import com.example.server.service.ChatService;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@RestController
@ServerEndpoint("/{userId}")
public class ChatController {

    public static ChatService chatService;


    /**
     * 会话连接一建立就执行,即用户登陆成功就将该用户名和该对话以键值对存储
     *
     * @param session  前后端会话
     * @param userId 前后端会话的用户名
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        chatService.onOpen(session, userId);
    }

    /**
     * 会话结束时执行,将该用户和他的会话键值对从hashmap中删除
     *
     * @param userId 会话结束的用户的用户名
     */
    @OnClose
    public void onClose(@PathParam("userId") Integer userId) {
        chatService.onClose(userId);
    }

    /**
     * 前端发送请求时
     *
     * @param message 用户发送的Message类型的数据
     */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") Integer userId) {
        chatService.onMessage(message,userId);
    }

    /**
     * 发生错误时执行
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        chatService.onError(session, error);
    }
}
