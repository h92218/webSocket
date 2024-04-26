package com.example.websocket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WebsocketErrorDto {
    private int status;
    private String message;
    private String code;
    private String timestamp = LocalDateTime.now().toString();

    public String toJSONString(){
        return "{\"status\" : "+this.status + ", \"message\" : \""+this.message+"\", \"code\" : \""+this.code+"\", \"timestamp\" : \""+timestamp+"\"}";
    }
}