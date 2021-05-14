package models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Game {

    String id;
    User player1;
    User player2;
    Board board;
    @Setter
    GameState state;
    @Setter
    PlayerTurn playerTurn;

    public Game(User player1, User player2, Board board) {
        this.id = UUID.randomUUID().toString();
        this.state = GameState.IN_PROGRESS;
        this.playerTurn = PlayerTurn.PLAYER_ONE;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }
}
