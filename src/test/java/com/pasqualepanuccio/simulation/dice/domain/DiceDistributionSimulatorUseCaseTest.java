package com.pasqualepanuccio.simulation.dice.domain;

import com.pasqualepanuccio.simulation.dice.infrastructure.InMemoryDiceDistributionRepository;
import com.pasqualepanuccio.simulation.dice.infrastructure.StubDiceDistributionSimulator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class DiceDistributionSimulatorUseCaseTest {

    public static final int NUMBER_OF_DICE_SIDES = 6;
    public static final HashMap<Integer, Integer> STUBBED_DICE_DISTRIBUTION_MAP = new HashMap<>() {{
        put(1, 0);
        put(2, 0);
        put(3, 0);
        put(4, 0);
        put(5, 0);
        put(6, 1);
    }};
    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository = new InMemoryDiceDistributionRepository();
    private final Simulator diceDistributionSimulator = new StubDiceDistributionSimulator(STUBBED_DICE_DISTRIBUTION_MAP);
    private final DiceDistributionSimulatorUseCase useCase = new DiceDistributionSimulatorUseCase(diceDistributionSimulator, diceDistributionSimulationRepository);

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
        DiceDistributionSimulation expectedDiceDistributionSimulation = new DiceDistributionSimulation(
                numberOfMinimumDiceSides, numberOfDiceSides, numberOfDice, numberOfRolls, STUBBED_DICE_DISTRIBUTION_MAP);

        final DiceDistributionSimulationResponse response = useCase.run(request);

        Assertions.assertThat(response.isOk()).isTrue();
        Assertions.assertThat(diceDistributionSimulationRepository.findAll().get(0)).isEqualTo(expectedDiceDistributionSimulation);
    }
}