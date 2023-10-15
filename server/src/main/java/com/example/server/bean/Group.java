package com.example.server.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private int id;
    @JSONField(name = "name")
    private String groupName;
    private int maxNum;
    private int nowNum;
    private int ownId;
    private boolean leaf;
}
