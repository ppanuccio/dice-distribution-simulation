package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.NumberGenerator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generator(int maxBound) {
        return new Random().nextInt(maxBound) + 1;
    }
}
