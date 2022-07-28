package tictactoe;

enum State {
    DRAW,
    X_WINS,
    O_WINS,
    GAME_NOT_FINISHED
}

public class TicTacToe {
    int[][] winningPatterns = new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                             {0, 4, 8}, {2, 4, 6}};
    final static char X = 'X';
    final static char O = 'O';
    final static char EMPTY_CELL = '_';
    protected StringBuilder board;
    private State state;
    protected int movesPlayed;


    TicTacToe() {
        this.setNewBoard();
    }

    TicTacToe(StringBuilder board, int movesPlayed, State state) {
        this.board = board;
        this.movesPlayed = movesPlayed;
        this.state = state;
    }

    public char getEmptyCell() { return EMPTY_CELL; }
    public State getState() { return state; }
    public char getX() { return X; }
    public char getO() { return O; }
    protected void addMovesPlay() { this.movesPlayed++; }
    protected void minusMovesPlay() { this.movesPlayed--; }

    public void displayBoard() {
        System.out.println("---------");
        for (int i = 0; i < 9; i+=3) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (this.board.charAt(i + j) == EMPTY_CELL)
                    System.out.print("  ");
                else
                    System.out.print(this.board.charAt(i + j) + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void setBoard(StringBuilder _board) {
        this.setNewBoard();
        this.board = _board;
        for (int i = 0; i < 9; i++) {
            if (this.board.charAt(i) != this.getEmptyCell())
                this.addMovesPlay();
        }
        this.checkEnd();
    }

    protected int cooardinatesToIndex(int col, int row) {
        row--;
        col--;
        return row + (col * 3);
    }

    public void setMarkOnBoard(int col, int row, char sign) {
        this.board.setCharAt(cooardinatesToIndex(col, row), sign);
    }

    public void setMarkOnBoard(int idx) {
        this.board.setCharAt(idx, this.whomMove());
    }

    protected void setEmptyOnBoard(int idx) {
        this.board.setCharAt(idx, getEmptyCell());
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean checkIfOccupied(int col, int row) {
        return this.board.charAt(cooardinatesToIndex(col, row)) != EMPTY_CELL;
    }

    protected boolean checkIfOnBoard(int col, int row) {
        boolean rowCorrect = row >= 1 && row <=3;
        boolean colCorrect = col >= 1 && col <=3;
        return rowCorrect && colCorrect;
    }

    private void checkWin() {
        for (int[] line: winningPatterns) {
            if (areTwoCellsSameAndEmpty(line[0], line[1])
                    && areTwoCellsSameAndEmpty(line[0], line[2])) {
                state = this.board.charAt(line[0]) == X ? State.X_WINS : State.O_WINS;
                break;
            }
        }
    }

    protected boolean areTwoCellsSameAndEmpty(int c1, int c2) {
        /**
         * check if two given by id cells are the same and not empty
         * @return true if so, else false
         */
        return (this.board.charAt(c1) == this.board.charAt(c2) && this.board.charAt(c1) != EMPTY_CELL);
    }

    private void checkDraw() {
        if (this.movesPlayed == 9)
            this.state = State.DRAW;
    }

    public void checkEnd() {
        this.state = State.GAME_NOT_FINISHED;
        this.checkDraw();
        this.checkWin();
    }

    public void printState() {
        switch (state) {
            case X_WINS: {
                System.out.println("X wins");
                break;
            }
            case O_WINS: {
                System.out.println("O wins");
                break;
            }
            case DRAW: {
                System.out.println("Draw");
                break;
            }
        }
    }

    public char whomMove() {
        return (this.movesPlayed % 2 == 0) ? O : X;
    }

    int[] getEmptyCells() {
        int[] emptyCells = new int[9 - movesPlayed];
        int n = 0;
        for (int i = 0; i < this.board.length(); i++) {
            if (this.board.charAt(i) == EMPTY_CELL){
                emptyCells[n] = i;
                n++;
            }
        }
        return emptyCells;
    }

    public void setNewBoard() {
        this.state = State.GAME_NOT_FINISHED;
        this.board = new StringBuilder("_________");
        this.movesPlayed = 0;
    }
}
