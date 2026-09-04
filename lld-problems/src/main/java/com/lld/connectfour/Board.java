package com.lld.connectfour;

public class Board {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = Cell.EMPTY;
            }
        }
    }

    /** Drops a disc into a column. Returns the row it landed in, or -1 if the column is full. */
    public int drop(int col, Cell color) {
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Column out of range: " + col);
        }
        for (int r = rows - 1; r >= 0; r--) {
            if (grid[r][col] == Cell.EMPTY) {
                grid[r][col] = color;
                return r;
            }
        }
        return -1; // column full
    }

    public boolean isFull() {
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == Cell.EMPTY) {
                return false;
            }
        }
        return true;
    }

    /** Checks whether the disc just placed at (row,col) completes a line of 4. */
    public boolean isWinningMove(int row, int col) {
        Cell color = grid[row][col];
        if (color == Cell.EMPTY) {
            return false;
        }
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] d : directions) {
            int count = 1
                    + countDirection(row, col, d[0], d[1], color)
                    + countDirection(row, col, -d[0], -d[1], color);
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    private int countDirection(int row, int col, int dr, int dc, Cell color) {
        int count = 0;
        int r = row + dr;
        int c = col + dc;
        while (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == color) {
            count++;
            r += dr;
            c += dc;
        }
        return count;
    }

    public void print() {
        for (int r = 0; r < rows; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < cols; c++) {
                sb.append(grid[r][c].getSymbol()).append(' ');
            }
            System.out.println(sb.toString().trim());
        }
        StringBuilder idx = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            idx.append(c).append(' ');
        }
        System.out.println(idx.toString().trim());
    }
}
