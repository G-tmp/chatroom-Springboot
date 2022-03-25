package com.xd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatRoomTextHandler chatRoomTextHandler;

    @Autowired
    private WSConnectInterceptor wsConnectInterceptor;


    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatRoomTextHandler, "/room_ws/{roomId}")
                .addInterceptors(wsConnectInterceptor)
                .setAllowedOrigins("*");
    }
}