package com.example.common.bean;

import lombok.Data;

public enum StatusCode {

    //成功
    LoginSuccess(200),//登陆成功
    LogOut(201),//登出
    VerifySuccess(203),//邮箱验证码正确

    SingnupSuccess(202),//注册成功

     //失败
    NoAuthentication(300),//未认证
    NoAuthorize(301),//未授权
    LoginFail(302),//用户名或密码错误
    UserOnline(303),//用户异地登录


    UserNameHasExist(304),//用户名已存在
    EmailHasExist(305),//邮箱已使用
    VerifyError(306);//邮箱验证码错误

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    StatusCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code=" + code +
                '}';
    }
}
