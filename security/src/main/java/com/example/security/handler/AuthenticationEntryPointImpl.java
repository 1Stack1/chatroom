package com.example.security.handler;

import com.alibaba.fastjson.JSON;
import com.example.common.bean.Result;
import com.example.common.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {//认证失败异常处理
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result=new Result(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误",null);
        String s = JSON.toJSONString(result);
        WebUtils.renderString(response,s);
    }
}
