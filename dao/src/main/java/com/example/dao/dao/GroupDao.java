package com.example.dao.dao;


import com.example.common.bean.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupDao {

    List<Group> selectGroupsByUserId(int id);

    List<Group> selectGroupsByUserName(String username);
}
