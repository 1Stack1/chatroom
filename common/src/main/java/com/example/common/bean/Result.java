package com.example.common.bean;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
