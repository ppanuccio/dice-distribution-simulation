package com.pasqualepanuccio.simulation.dice.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceDistributionSimulatorUseCase {

    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository;
    private final NumberGenerator numberGenerator;

    public DiceDistributionSimulatorUseCase(DiceDistributionSimulationRepository diceDistributionSimulationRepository,
                                            NumberGenerator numberGenerator) {
        this.diceDistributionSimulationRepository = diceDistributionSimulationRepository;
        this.numberGenerator = numberGenerator;
    }

    public DiceDistributionSimulationRepository getDiceDistributionSimulationRepository() {
        return diceDistributionSimulationRepository;
    }

    public NumberGenerator getNumberGenerator() {
        return numberGenerator;
    }

    public DiceDistributionSimulationResponse run(DiceDistributionSimulationRequest request) {
        try {
            if (request.getNumberOfDice() < 1) {
                return DiceDistributionSimulationResponse.ko("The number of dice must be at least one!");
            }
            if (request.getNumberOfRolls() < 1) {
                return DiceDistributionSimulationResponse.ko("The number of rolls must be at least one!");
            }
            if (request.getDiceSides() < 4) {
                return DiceDistributionSimulationResponse.ko("The sides of a dice must be at least four!");
            }

            Map<Integer, Integer> diceDistribution = initialiseDiceDistribution(request.getNumberOfDice(), request.getDiceSides());
            final DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(
                    request.getMinimumDiceSides(),
                    request.getDiceSides(),
                    request.getNumberOfDice(),
                    request.getNumberOfRolls(), this.numberGenerator, diceDistribution);
            diceDistributionSimulation.execute();
            diceDistributionSimulationRepository.save(diceDistributionSimulation);
            return DiceDistributionSimulationResponse.ok(diceDistributionSimulation.getDiceDistribution());
        } catch (Exception e) {
            return DiceDistributionSimulationResponse.ko(e.getMessage());
        }
    }

    private Map<Integer, Integer> initialiseDiceDistribution(int numberOfDice, int diceSides) {
        final int maxSumValue = numberOfDice * diceSides;
        return IntStream.range(numberOfDice, maxSumValue + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> 0));
    }
}
