package com.rodemtree.projectboardadmin.controller;

import com.rodemtree.projectboardadmin.dto.WebSocketMessage;
import com.rodemtree.projectboardadmin.dto.security.BoardAdminPrincipal;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/chat")
    public WebSocketMessage chat(WebSocketMessage message, Principal principal) throws Exception {
        BoardAdminPrincipal boardAdminPrincipal = (BoardAdminPrincipal) ((Authentication) principal).getPrincipal();
        Thread.sleep(1000); // 대화하는 느낌을 시뮬레이션

        return WebSocketMessage.of("안녕하세요 " + boardAdminPrincipal.nickname() + "! " + message.content() + "라고 하셨나요?");
    }
}
