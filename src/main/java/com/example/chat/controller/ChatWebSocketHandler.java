package com.example.chat.controller;

import com.example.chat.domain.Message;
import com.example.chat.model.MessageRequest;
import com.example.chat.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.debug("New message: {}", payload);
        MessageRequest messageRequest = this.objectMapper.readValue(payload, MessageRequest.class);
        this.messageService.handleMessage(session, messageRequest);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.debug("Session closed: {}", session);
        MessageRequest messageRequest = this.messageService.newExitMessageOf(session.getId());
        this.messageService.handleMessage(session, messageRequest);
    }

}
