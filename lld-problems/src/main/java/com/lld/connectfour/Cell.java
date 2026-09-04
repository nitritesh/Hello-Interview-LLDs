package com.lld.connectfour;

/** A single cell on the board. */
public enum Cell {
    EMPTY("."),
    RED("R"),
    YELLOW("Y");

    private final String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
