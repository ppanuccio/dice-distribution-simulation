package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiceTest {

    public static final int MIN_SIDES_NUMBER = 1;
    public static final int DICE_SIDES = 6;
    private final NumberGenerator numberGenerator = new FakeRandomGenerator(DICE_SIDES);

    @Test
    void number_of_sides_must_be_greater_than_minimum_side_number() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            final Dice dice = new Dice(2, 1, numberGenerator);
        });

        assertEquals("Actual number of sides 1 must be greater than the minimum number of sides 2", thrown.getMessage());
    }

    @Test
    void roll_must_return_a_value_between_the_minimum_number_and_the_number_of_sides() {
        final Dice dice = new Dice(MIN_SIDES_NUMBER, DICE_SIDES, numberGenerator);

        Assertions.assertThat(dice.roll()).isGreaterThanOrEqualTo(MIN_SIDES_NUMBER);
        Assertions.assertThat(dice.roll()).isLessThanOrEqualTo(DICE_SIDES);
    }
}