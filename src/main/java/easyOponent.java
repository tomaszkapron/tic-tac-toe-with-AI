package tictactoe;

import java.util.Random;

@OpponentType("easy")
public class easyOponent extends Oponent{

    easyOponent(TicTacToe game) {
        super(game);
    }

    @Override
    int makeMove() {
        Random random = new Random();
        informMove();
        int[] moves = gameBoard.getEmptyCells();
        gameBoard.addMovesPlay();
        return moves[random.nextInt(moves.length)];
    }

    @Override
    void informMove() {
        System.out.println("Making move level \"easy\"");
    }
}
