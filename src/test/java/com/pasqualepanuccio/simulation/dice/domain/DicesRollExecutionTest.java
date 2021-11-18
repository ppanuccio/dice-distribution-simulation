package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DicesRollExecutionTest {

    public static final int DICE_SIDES = 6;
    private final NumberGenerator numberGenerator = new FakeRandomGenerator(DICE_SIDES);

    @Test
    void dice_roll_execution() {
        final int minSidesNumber = 4;
        final List<Dice> dice = List.of(
                new Dice(minSidesNumber, DICE_SIDES, numberGenerator),
                new Dice(minSidesNumber, DICE_SIDES, numberGenerator)
        );
        final DiceRollExecution diceRollExecution = new DiceRollExecution(dice);

        int result = diceRollExecution.execute();

        Assertions.assertThat(result).isGreaterThanOrEqualTo(dice.size());
        Assertions.assertThat(result).isLessThanOrEqualTo(dice.size() * DICE_SIDES);

    }
}