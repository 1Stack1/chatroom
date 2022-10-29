package com.example.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.common.bean.User;
import com.example.common.constant.OnlineUser;
import com.example.common.utils.RedisCache;
import com.example.dao.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class LogoutHandlerImpl implements LogoutHandler {

    @Autowired
    UserDao userDao;

    @Autowired
    RedisCache redisCache;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String username=null;
        //因为前端post请求发送的参数是application/json的方式要么使用@RequestBody要么使用流
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuffer sb=new StringBuffer();
            String s=null;
            while((s=br.readLine())!=null){
                sb.append(s);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            username = jsonObject.getString("username");
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user=new User();
        user.setUsername(username);
        logout(user);
    }

    public void logout(User user){
        //用户退出操作需要删除redis中的用户信息(否则用户退出后携带之前的token可以忽视JwtAuthenticationTokenFilter不用进行登录就可以进行操作)
        // 以及set中存储的用户信息(set中存储着已登录用户信息,不进行删除用户不能进行下次访问)
        String username=user.getUsername();

        String redisKey="login:"+username;
        redisCache.deleteObject(redisKey);

        OnlineUser.onlineUserSet.remove(username);

        userDao.updateStatusToDownLineByUsername(username);
    }
}
