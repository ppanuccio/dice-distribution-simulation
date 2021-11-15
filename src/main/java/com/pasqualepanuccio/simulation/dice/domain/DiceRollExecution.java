package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;

public class DiceRollExecution {

    private final List<Dice> dice;

    public DiceRollExecution(List<Dice> dice) {
        this.dice = dice;
    }

    public int execute() {
        return dice.stream()
                .map(Dice::roll)
                .reduce(0, Integer::sum);
    }
}
