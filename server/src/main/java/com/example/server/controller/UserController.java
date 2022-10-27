package com.example.server.controller;

import com.example.common.bean.Result;
import com.example.common.bean.User;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {
    @Autowired
    private UserService userService;


    //用户登录
    @PostMapping("/login")
    public  Result login(@RequestBody User user) throws Exception {
        return userService.login(user);
    }

    //用户注册
    @RequestMapping("/signup")
    public  Result singup(@RequestBody User user, HttpServletRequest request){
        return userService.singup(user,request);
    }


    //修改用户密保信息
    @RequestMapping("/updateInfo")
    public  int updateInfo(@RequestBody User user,HttpServletRequest request){
        return userService.updateInfo(user,request);
    }


    //验证用户发送的邮箱验证码
    @RequestMapping("/verify")
    public int verify(HttpServletRequest request){
        String username = request.getParameter("username");
        String verifyCode=request.getParameter("verifyCode");
        return userService.verify(username,verifyCode);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String hello(){
        return "hello";
    }


    //根据用户信息修改密码
    @PostMapping("/modifypw")
    public Result modifyPw(@RequestBody User user){
        return userService.modifyPw(user);
    }

}
