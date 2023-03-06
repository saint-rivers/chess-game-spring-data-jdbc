package com.example.demo.web;

import com.example.demo.feature.game.GameService;
import com.example.demo.web.request.FetchResponse;
import com.example.demo.web.request.game.GameRequest;
import com.example.demo.web.request.game.GameCreatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game")
public class ChessGameController {
    private final GameService gameService;

    @GetMapping("/{gameId}")
    public ResponseEntity<?> getGameById(@PathVariable Long gameId) {
        var response = gameService.findByGameId(gameId);
        return ResponseEntity.ok(
                FetchResponse.builder()
                        .message("fetched game")
                        .payload(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<?> createGame(@RequestBody GameRequest gameRequest) {
        var gameId = gameService.createGame(gameRequest);
        return ResponseEntity.ok(
                GameCreatedResponse.builder().message("created game").gameId(gameId).build()
        );
    }

    @PostMapping("/join/{gameId}")
    public ResponseEntity<?> joinGame(@RequestBody GameRequest gameRequest, @PathVariable Long gameId) {
        gameService.joinExistingGame(gameId, gameRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start/{gameId}")
    public ResponseEntity<?> startGame(@PathVariable Long gameId) {
        gameService.startGame(gameId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/end/{gameId}")
    public ResponseEntity<?> endGame(@PathVariable Long gameId) {
        gameService.endGame(gameId);
        return ResponseEntity.ok().build();
    }
}
