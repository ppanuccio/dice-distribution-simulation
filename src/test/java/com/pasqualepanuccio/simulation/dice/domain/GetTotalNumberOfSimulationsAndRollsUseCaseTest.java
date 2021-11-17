package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

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
                new DiceDistributionSimulation(minimumDiceSides, diceSides, diceNumber, numberOfRolls, numberGenerator, Collections.emptyMap())));

        TotalByDiceNumberAndDiceSides totalByDiceNumberAndDiceSides = useCase.run();

        Assertions.assertThat(totalByDiceNumberAndDiceSides.getDiceDistributionSimulations().size()).isEqualTo(1);
    }
}