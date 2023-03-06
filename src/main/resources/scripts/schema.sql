CREATE TABLE player
(
    id   serial PRIMARY KEY,
    name varchar(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS chess_game
(
    id         serial PRIMARY KEY,
    game_start timestamp NOT NULL DEFAULT NOW(),
    game_end   timestamp,

    CHECK ( game_end > game_start )
);

CREATE TABLE player_game
(
    player     integer NOT NULL REFERENCES player (id),     -- foreign key must match table name
    chess_game integer NOT NULL REFERENCES chess_game (id), -- foreign key must match table name
    PRIMARY KEY (player, chess_game)
);

CREATE OR REPLACE PROCEDURE player_join_game(IN game_id int8, IN player_id int8) AS
$$
BEGIN
    IF EXISTS(SELECT 1
              FROM player_game
              WHERE chess_game = game_id
              HAVING COUNT(player) >= 2) THEN
        RAISE EXCEPTION '2 players are already in the game';
    END IF;

    INSERT INTO player_game (player, chess_game) VALUES (player_id, game_id);


END;
$$ LANGUAGE plpgsql;
