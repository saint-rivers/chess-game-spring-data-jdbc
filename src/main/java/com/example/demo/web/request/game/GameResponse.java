package com.example.demo.web.request.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse{
    private Long id;
    private LocalDateTime gameStart;
    private LocalDateTime gameEnd;
    private Set<PlayerResponse> players;
}
