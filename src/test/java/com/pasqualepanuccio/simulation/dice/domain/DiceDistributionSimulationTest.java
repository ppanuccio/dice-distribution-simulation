package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DiceDistributionSimulationTest {

    @Test
    void dice_distribution_simulation() {
        final int numberOfDice = 3;
        final int numberOfDiceSides = 6;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfExecution = 10;
        int maxValue = numberOfDice * numberOfDiceSides;
        int minValue = numberOfDice;
        DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfExecution);

        final Map<Integer, Integer> result = diceDistributionSimulation.execute();

        Assertions.assertThat(result.entrySet().size()).isEqualTo(maxValue - minValue + 1);
    }

}