package com.example.server.dao;

import com.example.server.bean.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface GroupDao {
    List<Group> selectGroupsByUserName(@Param("username") String username);
}
