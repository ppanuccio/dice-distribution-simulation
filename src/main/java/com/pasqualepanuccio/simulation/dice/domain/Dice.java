package com.pasqualepanuccio.simulation.dice.domain;

public class Dice {

    private final int minSidesNumber;
    private final int sides;
    private final NumberGenerator numberGenerator;

    public Dice(int minSidesNumber, int sides, NumberGenerator numberGenerator) {
        if (sides < minSidesNumber) {
            throw new IllegalArgumentException(String.format("Actual number of sides %s must be greater than the minimum number of sides %s", sides, minSidesNumber));
        }
        this.minSidesNumber = minSidesNumber;
        this.sides = sides;
        this.numberGenerator = numberGenerator;
    }

    public int getSides() {
        return sides;
    }

    public int getMinSidesNumber() {
        return minSidesNumber;
    }

    public int roll() {
        return numberGenerator.generator(sides);
    }
}
