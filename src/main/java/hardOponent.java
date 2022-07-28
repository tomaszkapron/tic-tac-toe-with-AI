package tictactoe;

import java.util.*;

@OpponentType("hard")
public class hardOponent extends Oponent{

    TicTacToe gameCopy;

    hardOponent(TicTacToe game) {
        super(game);
    }

    private State playerToEndState(char player) {
        return player == gameCopy.getX() ? State.X_WINS : State.O_WINS;
    }

    private char opositePlayer(char player) {
        return player == gameCopy.getX() ? gameCopy.getO() : gameCopy.getX();
    }

    private StringBuilder convertBoard(StringBuilder board) {
        StringBuilder convertedBoard = new StringBuilder();
        convertedBoard.delete(0, convertedBoard.length());
        char[] convertedArr = new char[9];
        for (int i = 0; i < 9; i++) {
            char cell = board.charAt(i) == gameCopy.getEmptyCell() ? (char)i : board.charAt(i);
            convertedBoard.append(cell);
        }
        return convertedBoard;
    }

    private ArrayList<Integer> getEmptyCells(StringBuilder board){
        ArrayList<Integer> emptyCellsIndexes = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) != gameBoard.getX() && board.charAt(i) != gameBoard.getO())
                emptyCellsIndexes.add(i);
        }
        return emptyCellsIndexes;
    }

    private Move minimax(StringBuilder newBoard, char player, char currentPlayer) {
        ArrayList<Integer> availSpots = getEmptyCells(newBoard);

        gameCopy.checkEnd();
        if(gameCopy.getState() == playerToEndState(currentPlayer)) {
            gameCopy.setState(State.GAME_NOT_FINISHED);
            return new Move(10);
        }
        else if (gameCopy.getState() == State.DRAW) {
            gameCopy.setState(State.GAME_NOT_FINISHED);
            return new Move(0);
        }
        else if (gameCopy.getState() != State.GAME_NOT_FINISHED) {
            gameCopy.setState(State.GAME_NOT_FINISHED);
            return new Move(-10);
        }

        //create arr to collect all moves
        List<Move> moves = new ArrayList<Move>();

        for (int availSpot : availSpots) {
            Move move = new Move();
            move.index = newBoard.charAt(availSpot);

            gameCopy.addMovesPlay();
            gameCopy.setMarkOnBoard(move.index); //bug?

            newBoard.setCharAt(availSpot, player);

            Move result = minimax(newBoard, opositePlayer(player), currentPlayer);
            move.score = result.getScore();

            newBoard.setCharAt(availSpot, (char) move.index);
            gameCopy.setEmptyOnBoard(move.index);
            gameCopy.minusMovesPlay();

            moves.add(move);
        }

        Move bestMove = new Move();
        if (player == currentPlayer) {
            var maxScore = moves.stream().max(Comparator.comparingInt(Move::getScore));
            bestMove = maxScore.get();
        }
        else if (player == opositePlayer(currentPlayer)) {
            var minScore = moves.stream().min(Comparator.comparingInt(Move::getScore));
            bestMove = minScore.get();
        }

        return bestMove;
    }

    @Override
    int makeMove() {
        gameCopy = new TicTacToe(super.gameBoard.board, super.gameBoard.movesPlayed, super.gameBoard.getState());
        char playerr = opositePlayer(super.gameBoard.whomMove());
        StringBuilder boardToMinimax = convertBoard(gameBoard.board);
        Move move = minimax(boardToMinimax, playerr, playerr);
        gameBoard.addMovesPlay();
        informMove();
        return move.getIndex();
    }

    @Override
    void informMove() {
        System.out.println("Making move level \"hard\"");
    }
}

class Move {
    public int index;
    public int score;
    public static final Comparator<? super Move> BY_SCORE =
            Comparator.comparingInt(Move::getScore);

    Move() {}

    Move(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    public int getIndex() { return index; }

    @Override
    public String toString() {
        return "Move{" +
                "index=" + index +
                ", score=" + score +
                '}';
    }
};
