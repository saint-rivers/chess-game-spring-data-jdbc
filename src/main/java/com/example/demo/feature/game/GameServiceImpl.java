package com.example.demo.feature.game;

import com.example.demo.entity.Game;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.feature.player.PlayerRepository;
import com.example.demo.web.request.game.GameRequest;
import com.example.demo.web.request.game.GameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final JdbcTemplate jdbcTemplate;
    private final PlayerRepository playerRepository;

    @Override
    public Long createGame(GameRequest gameRequest) {
        var game = gameMapper.toGameEntity(gameRequest);
        var createdGame = gameRepository.save(game);
        return createdGame.getId();
    }

    @Override
    public void joinExistingGame(Long id, GameRequest gameRequest) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("unable to find game with id", id));
        existingGame.addPlayer(gameRequest.playerId());

        jdbcTemplate.update("CALL player_join_game(?, ?)", existingGame.getId(), gameRequest.playerId());
    }

    @Override
    public void startGame(Long id) {
        jdbcTemplate.update("UPDATE chess_game SET game_start = ? WHERE id = ?", LocalDateTime.now(), id);
    }

    @Override
    public void endGame(Long gameId) {
        jdbcTemplate.update("UPDATE chess_game SET game_end = ? WHERE id = ?", LocalDateTime.now(), gameId);
    }

    @Override
    public GameResponse findByGameId(Long gameId) {
        var game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RecordNotFoundException("unable to find game with id", gameId));
        var players = playerRepository.findPlayersByGameId(gameId);
        return gameMapper.toResponse(game, players);
    }
}
