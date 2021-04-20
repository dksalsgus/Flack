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

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public ResponseEntity<Chat> chat(Chat chat) throws InterruptedException {
        Chat newChat = chatService.chatSave(chat);
        Thread.sleep(1000);
        return ResponseEntity.ok(newChat);
    }
}
