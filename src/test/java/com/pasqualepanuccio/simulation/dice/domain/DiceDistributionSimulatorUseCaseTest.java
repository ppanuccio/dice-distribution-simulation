package com.pasqualepanuccio.simulation.dice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class DiceDistributionSimulatorUseCaseTest {

    public static final int NUMBER_OF_DICE_SIDES = 6;
    private final NumberGenerator fakeRandomGenerator = new FakeRandomGenerator(NUMBER_OF_DICE_SIDES);
    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository = Mockito.mock(DiceDistributionSimulationRepository.class);
    private final DiceDistributionSimulatorUseCase useCase = new DiceDistributionSimulatorUseCase(diceDistributionSimulationRepository, fakeRandomGenerator);

    @Test
    void number_of_dice_must_be_at_least_one() {
        final int numberOfDice = 0;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfRolls = 10;
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                numberOfMinimumDiceSides, NUMBER_OF_DICE_SIDES, numberOfDice, numberOfRolls);

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

    @Test
    void save_result_of_simulation() {
        final int numberOfDice = 1;
        final int numberOfDiceSides = 6;
        final int numberOfMinimumDiceSides = 1;
        final int numberOfRolls = 1;
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls);
        Map<Integer, Integer> expectedDiceDistributionMap = new HashMap<>() {{
            put(1, 0);
            put(2, 0);
            put(3, 0);
            put(4, 0);
            put(5, 0);
            put(6, 1);
        }};
        DiceDistributionSimulation expectedDiceDistributionSimulation = new DiceDistributionSimulation(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls, fakeRandomGenerator, expectedDiceDistributionMap);

        final DiceDistributionSimulationResponse response = useCase.run(request);

        Assertions.assertThat(response.isOk()).isTrue();
        Mockito.verify(diceDistributionSimulationRepository).save(expectedDiceDistributionSimulation);
    }
}