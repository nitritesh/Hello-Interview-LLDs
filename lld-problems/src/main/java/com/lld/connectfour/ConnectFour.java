package com.lld.connectfour;

/** Orchestrates a game between two players. */
public class ConnectFour {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;
    private boolean over;
    private Player winner;

    public ConnectFour(Player p1, Player p2, int rows, int cols) {
        this.board = new Board(rows, cols);
        this.players = new Player[]{p1, p2};
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /** Plays a move for the current player. Returns true if the move ended the game. */
    public boolean play(int col) {
        if (over) {
            throw new IllegalStateException("Game is already over");
        }
        Player player = getCurrentPlayer();
        int row = board.drop(col, player.getColor());
        if (row == -1) {
            System.out.println("Column " + col + " is full, choose another.");
            return false;
        }
        if (board.isWinningMove(row, col)) {
            over = true;
            winner = player;
        } else if (board.isFull()) {
            over = true; // draw
        } else {
            currentPlayerIndex = 1 - currentPlayerIndex;
        }
        return over;
    }

    public boolean isOver() {
        return over;
    }

    public Player getWinner() {
        return winner;
    }

    public Board getBoard() {
        return board;
    }
}
