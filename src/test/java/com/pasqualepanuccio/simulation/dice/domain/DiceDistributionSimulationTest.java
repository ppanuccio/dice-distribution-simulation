package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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
        Map<Integer, Integer> diceDistributionMap = initialiseMap();
        DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls, numberGenerator, diceDistributionMap);

        diceDistributionSimulation.execute();

        Assertions.assertThat(diceDistributionSimulation.getDiceDistribution().entrySet().size()).isEqualTo(maxValue - minValue + 1);
        Assertions.assertThat(diceDistributionSimulation.getDiceDistribution().get(numberOfDiceSides)).isEqualTo(numberOfRolls);
        Assertions.assertThat(diceDistributionSimulation.getDiceDistribution().get(numberOfDiceSides - 1)).isEqualTo(0);
    }

    private Map<Integer, Integer> initialiseMap() {
        return new HashMap<>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 0);
        }};
    }

}