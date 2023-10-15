package com.example.server.service;

import com.example.common.utils.Result;
import com.example.server.bean.User;


import javax.servlet.http.HttpServletRequest;


public interface UserService {
    /**
     * 登录操作
     */
    Result login(User user) throws Exception;

    /**
     * 注册操作
     */
    Result singup(User user, HttpServletRequest request);

    /**
     * 修改用户信息操作
     */
    Result updateInfo(User user);


    /**
     * 验证验证码操作
     */
    Result verify(String username, String verifyCode);


    Result modifyPw(User user);
}
