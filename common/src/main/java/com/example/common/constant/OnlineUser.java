package com.example.common.constant;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineUser {
    public static final Map<String, Session> onlineUserMap = new ConcurrentHashMap<>();
    public static final Set<String> onlineUserSet=new HashSet<>();
}
