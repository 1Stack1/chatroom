package com.example.server.service.impl;


import com.example.server.dao.UserDao;
import com.example.server.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户名不存在");
        }
        List<String> list = userDao.selectRoleByUsername(username);
        return new User(user.getUsername(), user.getPassword(), list);
    }
}
