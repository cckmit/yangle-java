package com.ater.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler
{

    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // ...
    }
}
