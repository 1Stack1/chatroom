package com.example.common.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Friend {
    private int id;
    private int friendId;
    private String friendNote;
    private int friendStatus;
}
