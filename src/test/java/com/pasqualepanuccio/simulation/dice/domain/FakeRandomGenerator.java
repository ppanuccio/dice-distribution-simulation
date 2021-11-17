package com.pasqualepanuccio.simulation.dice.domain;

public class FakeRandomGenerator implements NumberGenerator {

    @Override
    public int generator(int maxBound) {
        return maxBound;
    }
}
