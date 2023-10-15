package com.example.server.dao;

import com.example.server.bean.History;
import com.example.server.bean.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrganizationDao {
    List<Organization> findOrgs(int userId);

    List<History> findHistoriesOfOrg(int orgId);

    List<Integer> findAllUserOfOrg(int orgId);

    int updateLastViewHistoryId(@Param("orgId") int orgId,
                                @Param("userId") int userId,
                                @Param("historyId") int historyId);
}
