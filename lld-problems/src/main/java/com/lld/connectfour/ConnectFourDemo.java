package com.lld.connectfour;

/** Runnable example. A scripted game where RED wins with a vertical four. */
public class ConnectFourDemo {
    public static void main(String[] args) {
        Player red = new Player("Alice", Cell.RED);
        Player yellow = new Player("Bob", Cell.YELLOW);
        ConnectFour game = new ConnectFour(red, yellow, 6, 7);

        // Alice stacks column 3; Bob spreads across column 4.
        int[] moves = {3, 4, 3, 4, 3, 4, 3};
        for (int col : moves) {
            Player mover = game.getCurrentPlayer();
            System.out.println(mover.getName() + " (" + mover.getColor().getSymbol() + ") drops in column " + col);
            boolean ended = game.play(col);
            System.out.println();
            game.getBoard().print();
            System.out.println();
            if (ended) {
                break;
            }
        }

        if (game.getWinner() != null) {
            System.out.println("Winner: " + game.getWinner().getName());
        } else {
            System.out.println("Game ended in a draw.");
        }
    }
}
