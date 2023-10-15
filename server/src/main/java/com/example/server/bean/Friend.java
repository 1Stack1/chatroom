package com.example.server.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    private int id;
    @JSONField(name = "name")
    private String friendNote;
    private int friendId;
    private int groupId;
    private boolean leaf = true;
}
