package com.example.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ExecutionException;

@Component
public class WebSocketListener {

    @EventListener(ApplicationReadyEvent.class)
    public void setupWebSocketListener() throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();
        client.doHandshake(new TextWebSocketHandler(){
            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                System.out.println("Got text message: " + message.getPayload());
            }
        }, "ws://localhost:8080/greeting").get();


    }

}
