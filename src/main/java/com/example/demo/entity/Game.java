package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("chess_game")
public class Game {
    @Id
    private Long id;
    private LocalDateTime gameStart;
    private LocalDateTime gameEnd;
    private Set<PlayerRef> players = new HashSet<>();

    public void addPlayer(Long playerId) {
        this.players.add(new PlayerRef(playerId));
    }
}
