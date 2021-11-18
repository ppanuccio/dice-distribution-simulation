package com.pasqualepanuccio.simulation.dice.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetTotalNumberOfSimulationsAndRollsUseCaseTest {

    private DiceDistributionSimulationRepository diceDistributionSimulationRepository = Mockito.mock(DiceDistributionSimulationRepository.class);
    private final GetTotalNumberOfSimulationsAndRollsUseCase useCase = new GetTotalNumberOfSimulationsAndRollsUseCase(diceDistributionSimulationRepository);

    @Test
    void return_total_simulations_and_rolls() {
        int diceNumber = 3;
        int diceSides = 6;
        int minimumDiceSides = 1;
        int numberOfRolls = 10;
        NumberGenerator numberGenerator = new FakeRandomGenerator();
        Mockito.when(diceDistributionSimulationRepository.findAll()).thenReturn(List.of(
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls, numberGenerator, Collections.emptyMap()),
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls * 2, numberGenerator, Collections.emptyMap()),
                new DiceDistributionSimulation(minimumDiceSides, 4, 1, numberOfRolls, numberGenerator, Collections.emptyMap())));

        TotalByDiceNumberAndDiceSides totalByDiceNumberAndDiceSides = useCase.run();

        assertThat(totalByDiceNumberAndDiceSides.getSumByDiceNumberAndDiceSides().size()).isEqualTo(2);
        assertThat(totalByDiceNumberAndDiceSides.getSumByDiceNumberAndDiceSides()).containsExactlyInAnyOrder(
                aSumByDiceNumberAndDiceSides(diceNumber, diceSides, 2, numberOfRolls * 3, new HashMap<Integer, Integer>() {{
                    put(0, 0);
                }}),
                aSumByDiceNumberAndDiceSides(1, 4, 1, numberOfRolls, new HashMap<Integer, Integer>() {{
                    put(0, 0);
                }})
        );
    }

    private SumByDiceNumberAndDiceSides aSumByDiceNumberAndDiceSides(int diceNumber, int diceSides, int numberOfSimulation, int numberOfRolls, HashMap<Integer, Integer> diceDistribution) {
        return new SumByDiceNumberAndDiceSides(
                String.format("(%s,%s)", diceNumber, diceSides),
                new SumByDiceNumberAndDiceSides.Details(numberOfSimulation, numberOfRolls));
    }
}