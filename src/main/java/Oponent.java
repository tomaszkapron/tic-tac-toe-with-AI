package tictactoe;

abstract public class Oponent {
    TicTacToe gameBoard;

    Oponent(TicTacToe game) {
        this.gameBoard = game;
    }

    abstract int makeMove();
    abstract void informMove();
}
