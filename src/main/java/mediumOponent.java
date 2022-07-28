package tictactoe;

import java.util.Random;

@OpponentType("medium")
public class mediumOponent extends Oponent{

    mediumOponent(TicTacToe game) {
        super(game);
    }

    @Override
    int makeMove() {
        int bestMove = -1;
        int[][] check = new int[][] {{0, 1, 2}, {0, 2, 1}, {1, 2, 0}};
        for (int[] line: gameBoard.winningPatterns) {
            for (int[] posLin: check){
                if (super.gameBoard.areTwoCellsSameAndEmpty(line[posLin[0]], line[posLin[1]])
                && super.gameBoard.board.charAt(line[posLin[2]]) == gameBoard.getEmptyCell()) {
                    bestMove = line[posLin[2]];
                    if (super.gameBoard.board.charAt(line[posLin[0]]) == gameBoard.whomMove()){
                        informMove();
                        super.gameBoard.addMovesPlay();
                        return bestMove;
                    }
                }
            }
        }

        if (bestMove != -1){
            informMove();
            super.gameBoard.addMovesPlay();
            return bestMove;
        }
        else {
            Random random = new Random();
            informMove();
            int[] moves = gameBoard.getEmptyCells();
            gameBoard.addMovesPlay();
            return moves[random.nextInt(moves.length)];
        }
    }

    @Override
    void informMove() {
        System.out.println("Making move level \"medium\"");
    }
}
