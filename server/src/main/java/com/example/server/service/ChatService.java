package com.example.server.service;

import javax.websocket.Session;


public interface ChatService {

    /**
     *websocket onopen操作
     */
    void onOpen(Session session,String username);

    /**
     * websocket onclose操作
     */
    void onClose(String username);

    /**
     * websocket onmessage操作
     */
    void onMessage(String message);

    /**
     * websocket onerror操作
     */
    void onError(Session session,Throwable error);

}
