package com.example.chat.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionRepository {
    private Map<String, String> session2GroupId = new HashMap<>();

    public void save(String groupId, WebSocketSession webSocketSession) {
        this.session2GroupId.put(webSocketSession.getId(), groupId);
    }

    public void remove(String sessionId) {
        this.session2GroupId.remove(sessionId);
    }

    public String findGroupBySessionId(String sessionId) {
        return this.session2GroupId.getOrDefault(sessionId, "");
    }
}
