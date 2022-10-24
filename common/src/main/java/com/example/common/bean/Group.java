package com.example.common.bean;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private int id;
    private String groupName;
    private int maxNum;
    private List<Friend> friendList;
}
