package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiceDistributionSimulatorUseCaseTest {

    private NumberGenerator fakeRandomGenerator = new FakeRandomGenerator();
    private DiceDistributionSimulatorUseCase useCase = new DiceDistributionSimulatorUseCase(fakeRandomGenerator);

    @Test
    void number_of_dice_must_be_at_least_one() {
        final int numberOfDice = 0;
        final int numberOfDiceSides = 6;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfRolls = 10;
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls);

        final DiceDistributionSimulationResponse response = useCase.run(request);

        Assertions.assertThat(response.isKo()).isTrue();
        Assertions.assertThat(response.getErrorMessage()).isEqualTo("The number of dice must be at least one!");
    }

    @Test
    void number_of_rolls_must_be_at_least_one() {
        final int numberOfDice = 1;
        final int numberOfDiceSides = 6;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfRolls = 0;
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls);

        final DiceDistributionSimulationResponse response = useCase.run(request);

        Assertions.assertThat(response.isKo()).isTrue();
        Assertions.assertThat(response.getErrorMessage()).isEqualTo("The number of rolls must be at least one!");
    }

    @Test
    void the_sides_of_a_dice_must_be_at_least_four() {
        final int numberOfDice = 1;
        final int numberOfDiceSides = 3;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfRolls = 1;
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls);

        final DiceDistributionSimulationResponse response = useCase.run(request);

        Assertions.assertThat(response.isKo()).isTrue();
        Assertions.assertThat(response.getErrorMessage()).isEqualTo("The sides of a dice must be at least four!");
    }
}