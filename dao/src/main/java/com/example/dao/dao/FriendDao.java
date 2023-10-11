package com.example.dao.dao;

import com.example.common.bean.Friend;
import com.example.common.bean.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendDao {
    List<Friend> selectFriendsByGroupId(@Param("groupId") Integer groupId);
}
