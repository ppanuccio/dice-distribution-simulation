package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DicesRollExecutionTest {

    private final NumberGenerator numberGenerator = new FakeRandomGenerator();

    @Test
    void dice_roll_execution() {
        final int sides = 6;
        final int minSidesNumber = 4;
        final List<Dice> dice = List.of(
                new Dice(minSidesNumber, sides, numberGenerator),
                new Dice(minSidesNumber, sides, numberGenerator)
        );
        final DiceRollExecution diceRollExecution = new DiceRollExecution(dice);

        int result = diceRollExecution.execute();

        Assertions.assertThat(result).isGreaterThanOrEqualTo(dice.size());
        Assertions.assertThat(result).isLessThanOrEqualTo(dice.size() * sides);

    }
}