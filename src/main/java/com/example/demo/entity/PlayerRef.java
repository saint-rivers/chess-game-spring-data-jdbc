package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("player_game")
@Data
@AllArgsConstructor
public class PlayerRef {
    private Long player;
}
