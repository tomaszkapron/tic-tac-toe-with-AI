package tictactoe;

import java.util.Scanner;

public class GameInterface {

    TicTacToe gameBoard;
    boolean EXIT = true;

    GameInterface(TicTacToe board) {
        this.gameBoard = board;
    }

    public void takeCells(StringBuilder s) {
        gameBoard.setBoard(s);
    }

    public String[] takeCommand() {
        while(true) {
            try {
                System.out.println("Input command: ");
                Scanner scanner = new Scanner(System.in);
                String wholeCommand = scanner.nextLine();
                String[] parts = wholeCommand.split(" ");

                if (wholeCommand.equals("exit")) {
                    EXIT = false;
                    break;
                }
                if(parts.length != 3) {
                    System.out.println("Bad parameters!");
                    continue;
                }
                String command = parts[0];
                String opponent1 = parts[1];
                String opponent2 = parts[2];
                if (command.equals("start")) {
                    return parts;
                }else {
                    System.out.println("Bad parameters!");
                }
            } catch (Exception ex) {
                System.out.println("Bad parameters!");
            }
        }
        return null;
    }

    private Oponent[] process(String opponent1, String opponent2) {
        Oponent op1 = retriveOponent(opponent1);
        Oponent op2 = retriveOponent(opponent2);
        return new Oponent[] {op1, op2};
    }

    private Oponent retriveOponent(String keyword) {
        /**
         * return instatnce of specific opponent based on anntoation accordingly to keyword passed
         * @keyword: String -> type of opponent you are looking for to get.
         * @return: Oponent
         */
        Oponent[] opponents = {new userOponent(gameBoard),
                                new easyOponent(gameBoard),
                                new mediumOponent(gameBoard),
                                new hardOponent(gameBoard)};

        for (Oponent opponent: opponents) {
            OpponentType opponentType = opponent.getClass().getAnnotation(OpponentType.class);
            String name = opponentType.value();
            if(keyword.equalsIgnoreCase(name))
                return opponent;
        }
        return null;
    }

    public void runGame() {
        while(EXIT){
            String[] command = takeCommand();
            if (!EXIT)
                break;
            Oponent[] opponents = process(command[1], command[2]);
            Oponent op1 = opponents[0];
            Oponent op2 = opponents[1];
            //this.takeCells(new StringBuilder("XOX_OO_X_")); //debuging mode


            gameBoard.displayBoard();

            while (gameBoard.getState() == State.GAME_NOT_FINISHED) {
                gameBoard.setMarkOnBoard(op1.makeMove());
                gameBoard.displayBoard();
                gameBoard.checkEnd();
                if (gameBoard.getState() != State.GAME_NOT_FINISHED)
                    break;
                gameBoard.setMarkOnBoard(op2.makeMove());
                gameBoard.displayBoard();
                gameBoard.checkEnd();
            }
            gameBoard.printState();
            gameBoard.setNewBoard();
        }
    }
}
