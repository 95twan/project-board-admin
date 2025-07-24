package com.rodemtree.projectboardadmin.dto;

public record WebSocketMessage(String content) {
    public static WebSocketMessage of(String content) {
        return new WebSocketMessage(content);
    }
}
