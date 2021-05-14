package models;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Board {

    private int boardSize;
    private char[][] board;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            Arrays.fill(board[row], '.');
        }
    }
}
