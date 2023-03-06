package com.example.demo.feature.game;

import com.example.demo.entity.Game;
import com.example.demo.entity.Player;
import com.example.demo.entity.PlayerRef;
import com.example.demo.web.request.game.GameRequest;
import com.example.demo.web.request.game.GameResponse;
import com.example.demo.web.request.game.PlayerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameMapper {
    private final ModelMapper mapper;

    public GameMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Game toGameEntity(GameRequest gameRequest) {
        return Game.builder()
                .id(null)
                .gameStart(null)
                .gameEnd(null)
                .players(new HashSet<>() {{
                    add(new PlayerRef(gameRequest.playerId()));
                }})
                .build();
    }

    public GameResponse toResponse(Game game, Set<Player> players) {
        GameResponse response = mapper.map(game, GameResponse.class);
        Set<PlayerResponse> mappedPlayers = players.stream()
                .map(p -> this.mapper.map(p, PlayerResponse.class))
                .collect(Collectors.toSet());

        response.setPlayers(mappedPlayers);
        return response;
    }
}
