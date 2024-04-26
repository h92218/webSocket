package com.example.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    String memberId;
    String orderId;
    String status;
    String message;
    String orderType;

}
