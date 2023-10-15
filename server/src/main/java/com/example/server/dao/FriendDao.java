package com.example.server.dao;

import com.example.server.bean.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FriendDao {
    List<Friend> selectFriendsByGroupId(@Param("groupId") Integer groupId);
}
