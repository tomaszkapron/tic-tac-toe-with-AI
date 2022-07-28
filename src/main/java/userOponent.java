package tictactoe;

import java.util.Scanner;

@OpponentType("user")
public class userOponent extends Oponent{

    userOponent(TicTacToe game) {
        super(game);
    }

    @Override
    int makeMove() {
        while(true){
            int row;
            int col;
            Scanner sc = new Scanner(System.in);
            informMove();
            try {
                row = sc.nextInt();
                col = sc.nextInt();
            }
            catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (!gameBoard.checkIfOnBoard(row, col)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            else if (gameBoard.checkIfOccupied(row, col)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            char move = gameBoard.whomMove();
            gameBoard.addMovesPlay();
            return gameBoard.cooardinatesToIndex(row, col);
        }
    }

    @Override
    void informMove() {
        System.out.println("Enter the coordinates: ");
    }
}
