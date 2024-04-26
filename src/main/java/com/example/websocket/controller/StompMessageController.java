package com.example.websocket.controller;

import com.example.websocket.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class StompMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    //pub 테스트용
    @MessageMapping("/pub/{memberId}")
    public void pubMessageProcess(@DestinationVariable("memberId") String memberId, String message){
        System.out.println(memberId +"님 : " + message);
        messagingTemplate.convertAndSend("/topic/orderStatus/"+memberId, "outbound test");
    }

    //구독 확인용 메세지 전송용
    @SubscribeMapping("/subscribe/{memberId}")
    public String orderStatusProcess(@DestinationVariable("memberId") String memberId) throws Exception{
        MessageDto messageDto = new MessageDto();
        messageDto.setMemberId(memberId);
        messageDto.setMessage(memberId + "님 구독 연결 환영");
        String messageStr = new ObjectMapper().writeValueAsString(messageDto);
        log.info(messageStr);
        return messageStr;
        //throw new Exception("test exception");
    }

    //exceptionhandler 테스트용
    @MessageExceptionHandler
    public String handleException(Exception e){

        return "error : " + e.getMessage();
    }



}
