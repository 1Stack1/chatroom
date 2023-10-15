package com.example.server.dao;

import com.example.server.bean.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserDao {

    //根据用户名或邮箱和密码查询用户
    User selectUserByUsernameOrEmailAndPassword(@Param("usernameOrEmail") String usernameOrEmail,
                                                @Param("password") String password);

    User selectUserDto(@Param("username") String username,
                       @Param("password") String password);

    //判断用户名是否存在
    User selectByUsername(String username);

    //判断邮箱是否使用
    User selectByEmail(String email);


    //向user表中插入用户名和密码
    int insertUsernameAndPasswordAndEmail(@Param("username") String username,
                                          @Param("password") String password,
                                          @Param("email") String email);

    //根据用户名修改用户密保信息
    int updateInfoByUsername(@Param("realname") String realname,
                             @Param("college") String college,
                             @Param("username") String username);

    List<String> selectRoleByUsername(@Param("username") String username);


    @Update("UPDATE sys_user SET password=#{password} WHERE username=#{username} AND realname<=>#{realname} AND college<=>#{college} AND email<=>#{email}")
        //根据用户信息修改密码
    int updatePwByInfo(@Param("username") String username,
                       @Param("password") String password,
                       @Param("realname") String realname,
                       @Param("college") String college,
                       @Param("email") String email);

    int updateStatusToOnlineByUsername(String username);

    int updateStatusToDownLineByUsername(String username);
}
