package com.example.demo.feature.game;

import com.example.demo.web.request.game.GameRequest;
import com.example.demo.web.request.game.GameResponse;

public interface GameService {
    Long createGame(GameRequest gameRequest);
    void joinExistingGame(Long id, GameRequest gameRequest);
    void startGame(Long id);
    void endGame(Long gameId);
    GameResponse findByGameId(Long gameId);
}
