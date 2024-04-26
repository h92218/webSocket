package com.example.websocket.service;


import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import lombok.NoArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class StompOutboundHandler implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message;
    }
}
