package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DiceDistributionSimulationTest {

    public static final int NUMBER_OF_DICE_SIDES = 6;
    private final NumberGenerator numberGenerator = new FakeRandomGenerator(NUMBER_OF_DICE_SIDES);

    @Test
    void dice_distribution_simulation() {
        final int numberOfDice = 1;
        final int minimumDiceSides = 1;
        final int numberOfRolls = 10;
        int maxValue = numberOfDice * NUMBER_OF_DICE_SIDES;
        int minValue = numberOfDice;
        Map<Integer, Integer> diceDistributionMap = initialiseMap();
        DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(
                minimumDiceSides, NUMBER_OF_DICE_SIDES, numberOfDice, numberOfRolls, diceDistributionMap);
        List<Dice> diceList = List.of(new Dice(minimumDiceSides, NUMBER_OF_DICE_SIDES, numberGenerator));

        diceDistributionSimulation.execute(diceList);

        Assertions.assertThat(diceDistributionSimulation.getDiceDistribution().entrySet().size()).isEqualTo(maxValue - minValue + 1);
        Assertions.assertThat(diceDistributionSimulation.getDiceDistribution().get(NUMBER_OF_DICE_SIDES)).isEqualTo(numberOfRolls);
        Assertions.assertThat(diceDistributionSimulation.getDiceDistribution().get(NUMBER_OF_DICE_SIDES - 1)).isEqualTo(0);
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