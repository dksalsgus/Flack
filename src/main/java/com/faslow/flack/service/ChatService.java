package com.faslow.flack.service;

import com.faslow.flack.entity.chat.Chat;
import com.faslow.flack.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Chat chatSave(Chat chat) {
        Chat saveChat = chatRepository.save(chat);
        return saveChat;
    }

}
