package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiceDistributionSimulatorUseCaseTest {

    private DiceDistributionSimulatorUseCase useCase = new DiceDistributionSimulatorUseCase();

    @Test
    void distribution_simulation_use_case() {
        final int numberOfDice = 3;
        final int numberOfDiceSides = 6;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfExecution = 10;
        int maxValue = numberOfDice * numberOfDiceSides;
        int minValue = numberOfDice;
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfExecution);

        final DiceDistributionSimulationResponse response = useCase.run(request);

        Assertions.assertThat(response.getMap().entrySet().size()).isEqualTo(maxValue - minValue + 1);
    }
}