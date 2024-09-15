package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketController extends TextWebSocketHandler {

    private final SessionHolder sessionHolder;

    public WebSocketController(SessionHolder sessionHolder) {
        this.sessionHolder = sessionHolder;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionHolder.addSession(session);
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        sessionHolder.getSessions().stream().forEach(session -> {
            try {
                session.sendMessage(new TextMessage("hello"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
