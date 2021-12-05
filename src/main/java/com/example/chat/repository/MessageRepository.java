package com.example.chat.repository;

import com.example.chat.model.Message;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MessageRepository {
    private Map<String, Message> id2Message = new HashMap<>();

    public String save(Message message) {
        id2Message.put(message.getId(), message);
        return message.getId();
    }
}