package com.example.demo.web.request.game;

import lombok.Builder;

@Builder
public record GameRequest(Long playerId) {
}
