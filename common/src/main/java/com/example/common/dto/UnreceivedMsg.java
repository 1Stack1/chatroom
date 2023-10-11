package com.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UnreceivedMsg {
    private Integer fromId;
    private String content;
    private String fromUsername;
    private Integer count;
}
