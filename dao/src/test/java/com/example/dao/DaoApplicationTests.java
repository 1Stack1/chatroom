package com.example.dao;


import com.example.common.bean.Friend;
import com.example.common.bean.Group;
import com.example.dao.dao.FriendDao;
import com.example.dao.dao.GroupDao;
import com.example.dao.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DaoApplicationTests {

    @Autowired
    GroupDao groupDao;
    @Autowired
    FriendDao friendDao;

    @Test
    void contextLoads() {
        //List<Group> groups = groupDao.selectGroupsByUserId(5);
        List<Group> groups = groupDao.selectGroupsByUserName("1");
        for(Group group:groups){
            System.out.println(group.toString());
        }
    }

    @Test
    void test02(){
        List<Friend> friends = friendDao.selectFriendsByGroupId(6);
        for(Friend friend:friends){
            System.out.println(friend);
        }
    }
}
