package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulation;
import com.pasqualepanuccio.simulation.dice.domain.NumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class DiceDistributionSimulatorTest {

    private static final int DICE_SIDES = 6;
    private final NumberGenerator numberGenerator = new StubRandomGenerator(DICE_SIDES);
    DiceDistributionSimulator diceDistributionSimulator = new DiceDistributionSimulator(numberGenerator);

    @Test
    void simulate() {
        int minimumDiceSides = 4;
        int numberOfDice = 1;
        int numberOfRolls = 10;
        Map<Integer, Integer> expected = new HashMap<>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 10);
        }};
        final DiceDistributionSimulation simulation = diceDistributionSimulator.simulate(minimumDiceSides, numberOfDice, DICE_SIDES, numberOfRolls);

        Assertions.assertThat(simulation.getDiceDistribution()).isEqualTo(expected);
    }
}