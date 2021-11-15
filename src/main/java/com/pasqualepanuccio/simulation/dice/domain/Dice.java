package com.pasqualepanuccio.simulation.dice.domain;

import java.util.Random;

public class Dice {

    private final int minSidesNumber;
    private final int sides;

    public Dice(int minSidesNumber, int sides) {
        if (sides < minSidesNumber) {
            throw new IllegalArgumentException(String.format("Actual number of sides %s must be greater than the minimum number of sides %s", sides, minSidesNumber));
        }
        this.minSidesNumber = minSidesNumber;
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }

    public int roll() {
        return new Random().nextInt(sides) + minSidesNumber;
    }
}
