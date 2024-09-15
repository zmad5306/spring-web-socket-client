package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionHolder {
    private List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }
}
