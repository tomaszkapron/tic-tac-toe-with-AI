package tictactoe;

public class Main {

    public static void main(String[] args) {
        TicTacToe GameBoard = new TicTacToe();
        GameInterface GameIntr = new GameInterface(GameBoard);
        GameIntr.runGame();
    }
}
