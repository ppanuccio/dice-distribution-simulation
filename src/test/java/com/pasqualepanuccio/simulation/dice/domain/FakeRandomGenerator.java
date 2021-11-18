package com.pasqualepanuccio.simulation.dice.domain;

public class FakeRandomGenerator implements NumberGenerator {

    private final int diceSides;

    public FakeRandomGenerator(int diceSides) {
        this.diceSides = diceSides;
    }

    public int getDiceSides() {
        return diceSides;
    }

    @Override
    public int generator(int maxBound) {
        return diceSides;
    }
}
