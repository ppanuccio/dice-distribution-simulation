package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DiceRelativeDistributionTest {

    @Test
    void absolute_distribution() {
        final int diceSides = 4;
        List<DiceDistributionSimulation> diceDistributionSimulations = List.of(
                new DiceDistributionSimulation(1, diceSides, 1, 100, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 100);
                }}),
                new DiceDistributionSimulation(1, diceSides, 1, 200, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 200);
                }}),
                new DiceDistributionSimulation(1, diceSides, 1, 200, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 50);
                }})
        );
        DiceRelativeDistribution diceRelativeDistribution = new DiceRelativeDistribution();

        final Map<Integer, Integer> absoluteDistribution = diceRelativeDistribution.absoluteDistribution(diceDistributionSimulations);

        Assertions.assertThat(absoluteDistribution).isEqualTo(new HashMap<Integer, Integer>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 350);
        }});
    }

    @Test
    void relative_distribution() {
        int totalRolls = 350;
        final int diceSides = 4;
        List<DiceDistributionSimulation> diceDistributionSimulations = List.of(
                new DiceDistributionSimulation(1, diceSides, 1, 100, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 100);
                }}),
                new DiceDistributionSimulation(1, diceSides, 1, 200, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 200);
                }}),
                new DiceDistributionSimulation(1, diceSides, 1, 200, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 50);
                }})
        );
        DiceRelativeDistribution diceRelativeDistribution = new DiceRelativeDistribution();

        final Map<Integer, Double> relative = diceRelativeDistribution.relativeDistribution(diceDistributionSimulations, totalRolls);

        Assertions.assertThat(relative).isEqualTo(new HashMap<Integer, Double>() {{
            put(1, 0.0);
            put(2, 0.0);
            put(3, 0.0);
            put(4, 100.0);
        }});
    }
}