package com.example.dao.dao;

import com.example.common.bean.History;
import com.example.common.dto.UnreceivedMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryDao {
    List<History> selectHistoryById(@Param("userId") int userId,
                                    @Param("toUserId") int toUserId);
    void addHistory(@Param("id") int id,
                    @Param("fromId") int fromId,
                    @Param("toId") int toId,
                    @Param("content") String content,
                    @Param("state") int state);

    void addHistoryList(List<History> histories);

    List<UnreceivedMsg> getUnrecivedMsgs(int userId);

    int updateUnreceive(@Param("fromId") int fromId,
                        @Param("toId") int toId);
}
