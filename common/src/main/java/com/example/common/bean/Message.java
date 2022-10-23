package com.example.common.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "historyMsg")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    private String id;
    private String time;
    private String to;
    private String from;
    private String msg;
    private List<String> userNames;
}
