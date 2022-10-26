package com.example.server.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.bean.Friend;
import com.example.common.bean.Group;
import com.example.common.bean.User;
import com.example.dao.dao.FriendDao;
import com.example.dao.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {
    @Autowired
    GroupDao groupDao;
    @Autowired
    FriendDao friendDao;

    @PostMapping("/groupPage")
    public String showGroupPage(@RequestBody User user){
        List<Group> groups = groupDao.selectGroupsByUserName(user.getUsername());
        return JSON.toJSONString(groups);
    }

    @PostMapping("/friendPage")
    public String showFriendPage(@RequestBody Group group){
        List<Friend> friends = friendDao.selectFriendsByGroupId(group.getId());
        return JSON.toJSONString(friends);
    }
}
