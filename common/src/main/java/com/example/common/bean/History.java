package com.example.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private Integer id;
    private Integer toId;
    private Integer fromId;
    private String fromName;
    private String content;
    private Integer state;

    public History(Integer toId, Integer fromId, String content) {
        this.toId = toId;
        this.fromId = fromId;
        this.content = content;
    }
}
