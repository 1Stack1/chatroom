package com.example.server.service;

import javax.websocket.Session;


public interface ChatService {

    /**
     * websocket onopen操作
     */
    void onOpen(Session session, Integer userId);

    /**
     * websocket onclose操作
     */
    void onClose(Integer userId);

    /**
     * websocket onmessage操作
     */
    void onMessage(String message, Integer userId);

    /**
     * websocket onerror操作
     */
    void onError(Session session, Throwable error);

}
