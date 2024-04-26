package com.example.websocket.config;

import com.example.websocket.service.StompErrorHandler;
import com.example.websocket.service.StompInboundHandler;
import com.example.websocket.service.StompOutboundHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    private final StompInboundHandler stompInboundHandler;
    private final StompOutboundHandler stompOutboundHandler;
    private final StompErrorHandler stompErrorHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 클라이언트가 서버로 메세지를 보낼 때 붙여야하는 prefix
        //그 메세지는 payload를 말하는게 아니라 왔다갔다하는 프레임인듯
        registry.setApplicationDestinationPrefixes("/topic");

        //해당 문자열로 시작하는 message 주소값을 받아서 처리하는 Broker를 활성화한다.
        //클라이언트에게 메시지를 전달하는 역할
        registry.enableSimpleBroker("/topic"); //메세지 구독
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/websocket")
                .setAllowedOriginPatterns("*");
        registry.setErrorHandler(stompErrorHandler);
        // .withSockJS(); //웹소켓을 지원하지 않는 환경에서도 웹소켓 사용을 가능하게 해주는 옵션
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // connect / disconnect 인터셉터
        registration.interceptors(stompInboundHandler);

    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        // connect / disconnect 인터셉터
        registration.interceptors(stompOutboundHandler);

    }
}
