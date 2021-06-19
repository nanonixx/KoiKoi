package com.myproject.Object;

public class Yaku {

    private int points;

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    private String name;

    public Yaku(String name, int points) {
        this.points = points;
        this.name = name;
    }
}
