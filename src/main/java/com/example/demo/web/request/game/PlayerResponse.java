package com.example.demo.web.request.game;

import lombok.Builder;

@Builder
public record PlayerResponse(Long id, String name) {
}
