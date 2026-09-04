package com.lld.connectfour;

public class Player {
    private final String name;
    private final Cell color; // RED or YELLOW

    public Player(String name, Cell color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Cell getColor() {
        return color;
    }
}
