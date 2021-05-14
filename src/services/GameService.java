package services;

import models.*;
import repository.GameRepository;

import java.util.Arrays;
import java.util.Scanner;

public class GameService {

    private final Scanner inputScanner = new Scanner(System.in);

    public Game createGame(User player1, User player2, int boardSize) {
        Game newGame = new Game(player1, player2, new Board(boardSize));
        GameRepository.add(newGame);
        return newGame;
    }

    public void startGame(Game game) {
        System.out.println("--------------Welcome to TicTacToe-----------\n");

        while (game.getState() == GameState.IN_PROGRESS) {
            // get current player move
            Move move = getPlayerMove(game);

            // check if move is valid
            if (!isValidMove(move, game)) {
                continue;
            }

            // Play the move
            playMove(move, game);

            // update game status
            updateGameStatus(game);

            // print game board
            printBoard(game.getBoard());

            // flip player turn
            flipPlayerTurn(game);
        }

        printResult(game);
    }

    public void updateGameStatus(Game game) {
        GameState state = checkIfSomeoneWon(game);

        if (state == GameState.IN_PROGRESS) {
            if (checkIfBoardFilled(game)) {
                game.setState(GameState.DRAW); // draw
            } else {
                game.setState(GameState.IN_PROGRESS);
            }
        } else {
            game.setState(state); // win
        }
    }

    public void flipPlayerTurn(Game game) {
        if (game.getPlayerTurn() == PlayerTurn.PLAYER_ONE) {
            game.setPlayerTurn(PlayerTurn.PLAYER_TWO);
        } else if (game.getPlayerTurn() == PlayerTurn.PLAYER_TWO) {
            game.setPlayerTurn(PlayerTurn.PLAYER_ONE);
        }
    }

    public boolean isValidMove(Move move, Game game) {
        return move.getRow() >= 0
                && move.getRow() < game.getBoard().getBoardSize()
                && move.getCol() >= 0
                && move.getCol() < game.getBoard().getBoardSize()
                && game.getBoard().getBoard()[move.getRow()][move.getCol()] == UserSymbol.FREE;
    }

    public String getPattern(int len, char c) {
        char[] array = new char[len];
        Arrays.fill(array, c);
        return new String(array);
    }

    public GameState checkIfSomeoneWon(Game game) {
        Board board = game.getBoard();

        String playerOneWinningString = getPattern(board.getBoardSize(), game.getPlayer1().getSymbol());
        String playerTwoWinningString = getPattern(board.getBoardSize(), game.getPlayer2().getSymbol());

        // Horizontally
        for (int row = 0; row < board.getBoardSize(); row++) {
            String rowString = Arrays.toString(board.getBoard()[row]);

            if (rowString.equals(playerOneWinningString)) {
                return GameState.PLAYER_ONE_WON;
            } else if (rowString.equals(playerTwoWinningString)) {
                return GameState.PLAYER_TWO_WON;
            }
        }

        // Vertically
        for (int col = 0; col < board.getBoardSize(); col++) {
            StringBuilder colString = new StringBuilder();
            for (int row = 0; row < board.getBoardSize(); row++) {
                colString.append(board.getBoard()[row][col]);
            }

            if (colString.toString().equals(playerOneWinningString)) {
                return GameState.PLAYER_ONE_WON;
            } else if (colString.toString().equals(playerTwoWinningString)) {
                return GameState.PLAYER_TWO_WON;
            }
        }

        // Diagonally
        StringBuilder diaOneString = new StringBuilder();
        StringBuilder diaTwoString = new StringBuilder();
        for (int cell = 0; cell < board.getBoardSize(); cell++) {
            diaOneString.append(board.getBoard()[cell][cell]);
            diaTwoString.append(board.getBoard()[cell][board.getBoardSize() - cell - 1]);
        }

        if (diaOneString.toString().equals(playerOneWinningString)) {
            return GameState.PLAYER_ONE_WON;
        } else if (diaOneString.toString().equals(playerTwoWinningString)) {
            return GameState.PLAYER_TWO_WON;
        }

        if (diaTwoString.toString().equals(playerOneWinningString)) {
            return GameState.PLAYER_ONE_WON;
        } else if (diaTwoString.toString().equals(playerTwoWinningString)) {
            return GameState.PLAYER_TWO_WON;
        }

        return GameState.IN_PROGRESS;
    }

    public boolean checkIfBoardFilled(Game game) {
        Board board = game.getBoard();
        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                if (board.getBoard()[row][col] == UserSymbol.FREE) {
                    return false;
                }
            }
        }
        return true;
    }

    public Move getPlayerMove(Game game) {
        User player = getCurrentPlayer(game);
        System.out.println(player.getName() + ", please enter a valid row and column of your move : ");
        int row, col;
        row = inputScanner.nextInt();
        col = inputScanner.nextInt();
        return new Move(row, col);
    }

    public User getCurrentPlayer(Game game) {
        if (game.getPlayerTurn() == PlayerTurn.PLAYER_ONE) {
            return game.getPlayer1();
        } else if (game.getPlayerTurn() == PlayerTurn.PLAYER_TWO) {
            return game.getPlayer2();
        } else {
            return null;
        }
    }

    public void playMove(Move move, Game game) {
        game.getBoard().getBoard()[move.getRow()][move.getCol()] = getCurrentPlayer(game).getSymbol();
    }

    public void printBoard(Board board) {
        System.out.println(" - - -");
        for (int row = 0; row < board.getBoardSize(); row++) {
            System.out.print('|');
            for (int col = 0; col < board.getBoardSize(); col++) {
                System.out.print(board.getBoard()[row][col]);
                System.out.print('|');
            }
            System.out.println();
            System.out.println(" - - -");
        }
    }

    public void printResult(Game game) {
        if (game.getState() == GameState.DRAW) {
            System.out.println("######## GAME IS DRAW #########");
        } else if (game.getState() == GameState.PLAYER_ONE_WON) {
            System.out.println("######## " + game.getPlayer1().getName() + " WON #########");
        } else if (game.getState() == GameState.PLAYER_TWO_WON) {
            System.out.println("######## " + game.getPlayer2().getName() + " WON #########");
        }
    }
}
