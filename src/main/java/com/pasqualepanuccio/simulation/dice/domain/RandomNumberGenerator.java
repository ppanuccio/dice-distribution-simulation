package com.pasqualepanuccio.simulation.dice.domain;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generator(int maxBound) {
        return new Random().nextInt(maxBound) + 1;
    }
}
