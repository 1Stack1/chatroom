/*
package com.example.security.filter;


import com.example.common.bean.User;
import com.example.common.utils.JwtUtil;
import com.example.common.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
//将这个过滤器放到UsernamePasswordFilter之前,如果用户携带合法的token后面的关于认证的过滤器不在拦截
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        if (token == null) {
            filterChain.doFilter(request, response);//执行后面的过滤器
            return;//不执行下面直接返回

        }


        String username;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            username = claims.getSubject();//根据用户携带的token得到存储在redis中的key,value
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        String redisKey = "login:" + username;
        System.out.println(redisKey);
        User user = (User) redisCache.getCacheObject(redisKey);

        if (user == null) {//user不存在的情况是用户通过logout清除了redis中自己的数据
            throw new RuntimeException("用户已退出,请重新登录");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());//三个参数后面的过滤器默认用户已经认证
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);//将令牌放到这次请求Context中,以后的过滤器不会拦截除非最后的授权过滤器会拦截

        filterChain.doFilter(request, response);
    }
}
*/
