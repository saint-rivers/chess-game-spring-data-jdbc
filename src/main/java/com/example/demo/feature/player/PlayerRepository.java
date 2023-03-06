package com.example.demo.feature.player;


import com.example.demo.entity.Player;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query("SELECT p.* " +
            "FROM player_game pg JOIN player p " +
            "ON p.id = pg.player " +
            "WHERE pg.chess_game = :gameId")
    Set<Player> findPlayersByGameId(Long gameId);
}
