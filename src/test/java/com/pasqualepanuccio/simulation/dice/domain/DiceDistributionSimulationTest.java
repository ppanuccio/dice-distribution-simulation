package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DiceDistributionSimulationTest {

    private final NumberGenerator numberGenerator = new FakeRandomGenerator();

    @Test
    void dice_distribution_simulation() {
        final int numberOfDice = 1;
        final int numberOfDiceSides = 6;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfRolls = 10;
        int maxValue = numberOfDice * numberOfDiceSides;
        int minValue = numberOfDice;
        DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls, numberGenerator);

        final Map<Integer, Integer> result = diceDistributionSimulation.execute();

        Assertions.assertThat(result.entrySet().size()).isEqualTo(maxValue - minValue + 1);
        Assertions.assertThat(result.get(numberOfDiceSides)).isEqualTo(numberOfRolls);
        Assertions.assertThat(result.get(numberOfDiceSides - 1)).isEqualTo(0);
    }

}