package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.NumberGenerator;

public class StubRandomGenerator implements NumberGenerator {

    private final int diceSides;

    public StubRandomGenerator(int diceSides) {
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
