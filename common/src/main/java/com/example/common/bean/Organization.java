package com.example.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    private int id;
    private String orgName;
    private int maxNum;
    private int nowNum;
}
