package com.example.server.service.impl;

import com.example.common.utils.Result;
import com.example.common.constant.StatusCode;
import com.example.common.constant.OnlineUser;
import com.example.server.bean.User;
import com.example.server.dao.UserDao;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    public static final Set<String> set = OnlineUser.onlineUserSet;//存储已登录用户

    @Autowired
    UserDao userDao;

    /*@Autowired
    PasswordEncoder encoder;*/

    /*@Autowired
    MailService mailService;*/

    @Autowired
    AuthenticationManager authenticationManager;


    /**
     * 用户登录操作
     *
     * @param user 前端发送的User类型的json数据
     * @return 包含查询到的用户名和是否查询到用户的判断码
     */
    @Override
    public Result login(User user) {
        String username = user.getUsername();
        synchronized (this) {//HashMap线程不安全
            if (!set.contains(username)) {//已登陆账号列表中不存在该用户

                //自定义AuthenticationManager的执行

                //将username和password封装为UsernamePasswordAuthenticationToken,接下来使用该令牌与userDetail对比
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());


                //将封装好的UsernamePasswordAuthenticationToken作为参数执行authenticate方法,
                // 在之后会执行UserDetailService的loadUserByUsername方法,会将令牌与数据库中查询(UserDetailService执行)的UserDetails对比
                Authentication authenticate
                        = authenticationManager.authenticate(usernamePasswordAuthenticationToken);


                /*User user1 = (User) authenticate.getPrincipal();//将UserDetailsService中返回的UserDetails对象封装返回*/


                SecurityContextHolder.getContext().setAuthentication(authenticate);

                return new Result(StatusCode.LoginSuccess.getCode(),"success",null);
            } else {
                return new Result(StatusCode.UserOnline.getCode(), "用户已登录", null);
            }
        }


    }


    /**
     * 用户注册操作
     *
     * @param user    前端发送的User类型的json数据
     * @param request 前端的请求,目的是向本次注册对话(session)中加入用户名
     * @return 判断用户名是否存在的判断码
     */
    @Override
    public Result singup(User user, HttpServletRequest request) {
        /*String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        synchronized (this) {
            if (userDao.selectByUsername(username) != null && redisCache.getCacheSet("signupingUsername").contains(username)) {//用户名已存在
                System.out.println("用户名已存在");
                return new Result(StatusCode.UserNameHasExist.getCode(), "用户名已存在", null);
            } else if (userDao.selectByEmail(email) != null && redisCache.getCacheSet("signupingEmail").contains(email)) {//邮箱已被注册
                System.out.println("邮箱已被注册");
                return new Result(StatusCode.EmailHasExist.getCode(), "邮箱已被使用", null);
            } else {
                int number = new Random().nextInt(8999) + 1000;//制造验证码


                //todo 像邮箱发送验证码
                //mailService.sendSimpleEmail(email,"chatroom验证码","这是您的邮箱验证码："+number+"\n请在15分钟内将其输入");//向邮箱发送验证码
                System.out.println(number);


                //todo 将注册信息存放在redis中
                //将密码,邮箱,验证码以map形式存储在redis,key是用户的用户名,value是hashmap
                Map<String, String> map = new HashMap<>();
                map.put("password", password);
                map.put("email", email);
                map.put("code", "" + number);
                redisCache.setCacheMap(username, map);
                //将用户名,email存储到redis中
                redisCache.setCacheSet("signupingUsername", redisCache.getCacheSet("signupingUsername")).add(username);
                redisCache.setCacheSet("signupingEmail", redisCache.getCacheSet("signupingEmail")).add(username);


                redisCache.expire(username, 15, TimeUnit.MINUTES);//十五分钟后自动销毁
                return new Result(StatusCode.SingnupSuccess.getCode(), "注册成功,验证码已发送到邮箱,请在15分钟内输入", null);
            }
        }*/
        return null;
    }


    /**
     * 用户修改自己的密保信息
     *
     * @param user 前端发送的User类型的json数据
     * @return 判断用户是否注册完了在填写信息
     */
    @Override
    public Result updateInfo(User user) {
        /*String username = user.getUsername();
        String realname = user.getRealname();
        String college = user.getCollege();
        if (username != null) {
            userDao.updateInfoByUsername(realname, college, username);
            return new Result(StatusCode.UpdateInfoSuccess.getCode(), "用户信息修改成功", null);
        } else {
            return new Result(StatusCode.UpdateInfoFail.getCode(), "用户信息修改失败", null);
        }*/
        return null;
    }


    /**
     * 验证邮箱
     *
     * @param username   用户名
     * @param verifyCode 用户写的验证码
     * @return
     */
    @Override
    public Result verify(String username, String verifyCode) {
        /*Map<String, Object> cacheMap = redisCache.getCacheMap(username);
        //因为执行这步操作之前如果用户执行力signup,就会将用户输入的密码存储在以用户名为Key的redis hash中,那么密码就不会为null
        System.out.println(cacheMap.get("code"));
        if (cacheMap.get("password") != null) {
            String code = (String) cacheMap.get("code");//获取存储在redis中的验证码以和用户输入的相比较
            if (verifyCode.equals(code)) {
                String password = (String) cacheMap.get("password");
                String email = (String) cacheMap.get("email");
                userDao.insertUsernameAndPasswordAndEmail(username, password, email);
                return new Result(StatusCode.VerifySuccess.getCode(), "邮箱验证成功", null);
            }
        }
        return new Result(StatusCode.VerifyError.getCode(), "邮箱验证失败", null);*/
        return null;
    }


    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    @Override
    public Result modifyPw(User user) {
        /*int num = userDao.updatePwByInfo(user.getUsername(),
                encoder.encode(user.getPassword()),
                ("".equals(user.getRealname()) ? null : user.getRealname()),
                ("".equals(user.getCollege()) ? null : user.getCollege()),
                ("".equals(user.getEmail()) ? null : user.getEmail()));
        if (num == 1) {
            return new Result(200, "secuess", null);
        } else {
            return new Result(400, "fail", null);
        }*/
        return null;
    }

}
