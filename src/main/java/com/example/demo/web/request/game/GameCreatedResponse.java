package com.example.demo.web.request.game;

import lombok.Builder;

@Builder
public record GameCreatedResponse(String message, Long gameId) {
}
