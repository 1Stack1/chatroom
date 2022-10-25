package com.example.dao.dao;


import com.example.common.bean.Friend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendDao {

    List<Friend> selectFriendsByGroupId(int id);
}
