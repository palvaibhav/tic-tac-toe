package models;

import lombok.Getter;

@Getter
public class Move {
    int row;
    int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
