package com.pasqualepanuccio.simulation.dice.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

class GetTotalNumberOfSimulationsAndRollsUseCaseTest {

    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository = Mockito.mock(DiceDistributionSimulationRepository.class);
    private final DiceRelativeDistributionSummaryUseCase useCase = new DiceRelativeDistributionSummaryUseCase(diceDistributionSimulationRepository);

    @Test
    void return_total_simulations_and_rolls() {
        int diceNumber = 3;
        int diceSides = 6;
        int minimumDiceSides = 1;
        int numberOfRolls = 10;
        Mockito.when(diceDistributionSimulationRepository.findAll()).thenReturn(List.of(
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls, emptyMap()),
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls * 2, emptyMap()),
                new DiceDistributionSimulation(minimumDiceSides, 4, 1, numberOfRolls, emptyMap())));

        TotalByDiceNumberAndDiceSides totalByDiceNumberAndDiceSides = useCase.run();

        assertThat(totalByDiceNumberAndDiceSides.getSumByDiceNumberAndDiceSides().size()).isEqualTo(2);
        assertThat(totalByDiceNumberAndDiceSides.getSumByDiceNumberAndDiceSides()).containsExactlyInAnyOrder(
                aSumByDiceNumberAndDiceSides(diceNumber, diceSides, 2, numberOfRolls * 3, emptyMap()),
                aSumByDiceNumberAndDiceSides(1, 4, 1, numberOfRolls, emptyMap())
        );
    }

    @Test
    void return_relative_distribution() {
        int diceNumber = 1;
        int diceSides = 4;
        int minimumDiceSides = 1;
        int numberOfRolls = 1;
        Mockito.when(diceDistributionSimulationRepository.findAll()).thenReturn(List.of(
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, numberOfRolls);
                }}),
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls * 2, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, numberOfRolls * 2);
                }}),
                new DiceDistributionSimulation(minimumDiceSides, 5, 1, numberOfRolls, new HashMap<>() {{
                    put(1, 0);
                    put(2, 0);
                    put(3, 0);
                    put(4, 0);
                    put(5, numberOfRolls);
                }})));

        TotalByDiceNumberAndDiceSides totalByDiceNumberAndDiceSides = useCase.run();

        assertThat(totalByDiceNumberAndDiceSides.getSumByDiceNumberAndDiceSides().size()).isEqualTo(2);
        assertThat(totalByDiceNumberAndDiceSides.getSumByDiceNumberAndDiceSides()).containsExactlyInAnyOrder(
                aSumByDiceNumberAndDiceSides(diceNumber, diceSides, 2, numberOfRolls * 3,
                        new HashMap<>() {{
                            put(1, 0.0);
                            put(2, 0.0);
                            put(3, 0.0);
                            put(4, 100.0);
                        }}),
                aSumByDiceNumberAndDiceSides(1, 5, 1, numberOfRolls,
                        new HashMap<>() {{
                            put(1, 0.0);
                            put(2, 0.0);
                            put(3, 0.0);
                            put(4, 0.0);
                            put(5, 100.0);
                        }}));
    }

    private SumByDiceNumberAndDiceSides aSumByDiceNumberAndDiceSides(int diceNumber, int diceSides, int numberOfSimulation, int numberOfRolls, Map<Integer, Double> diceDistribution) {
        return new SumByDiceNumberAndDiceSides(
                String.format("(%s,%s)", diceNumber, diceSides),
                new SumByDiceNumberAndDiceSides.Details(numberOfSimulation, numberOfRolls, diceDistribution));
    }
}