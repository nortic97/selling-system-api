package com.nortic.posdash.ms.chat.config;

import com.nortic.posdash.ms.chat.controller.ActiveUsersWebSocketHandler;
import com.nortic.posdash.ms.chat.controller.ChatWebSocketHandler;
import com.nortic.posdash.ms.chat.service.MessageService;
import com.nortic.posdash.ms.chat.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageService messageService;
    private final UserServiceImpl userService;

    @Autowired
    public WebSocketConfig(MessageService messageService, UserServiceImpl userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(), "/api/v1/ms/chat").setAllowedOrigins("*");
        registry.addHandler(activeUsersWebSocketHandler(), "/api/v1/ms/chat/active-users").setAllowedOrigins("*");
    }

    @Bean
    public ChatWebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler(messageService, userService);
    }

    @Bean
    public ActiveUsersWebSocketHandler activeUsersWebSocketHandler() {
        return new ActiveUsersWebSocketHandler(userService);
    }
}
