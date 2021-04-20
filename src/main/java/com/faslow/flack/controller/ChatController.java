package com.faslow.flack.controller;

import com.faslow.flack.entity.chat.Chat;
import com.faslow.flack.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/pub") // 발행경로 /app/pub
    @SendTo("/topic/test") // 구독경로
    public ResponseEntity<Chat> chat(Chat chat) {
        Chat newChat = chatService.chatSave(chat);
        return ResponseEntity.ok(newChat);
    }
}
