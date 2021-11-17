package com.pasqualepanuccio.simulation.dice.domain;

import java.util.Map;

public class DiceDistributionSimulatorUseCase {

    private final NumberGenerator numberGenerator;

    public DiceDistributionSimulatorUseCase(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public NumberGenerator getNumberGenerator() {
        return numberGenerator;
    }

    public DiceDistributionSimulationResponse run(DiceDistributionSimulationRequest request) {
        if (request.getNumberOfDice() < 1) {
            return DiceDistributionSimulationResponse.ko("The number of dice must be at least one!");
        }
        if (request.getNumberOfRolls() < 1) {
            return DiceDistributionSimulationResponse.ko("The number of rolls must be at least one!");
        }
        if (request.getDiceSides() < 4) {
            return DiceDistributionSimulationResponse.ko("The sides of a dice must be at least four!");
        }
        final DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(
                request.getMinimumDiceSides(),
                request.getDiceSides(),
                request.getNumberOfDice(),
                request.getNumberOfRolls(), this.numberGenerator);
        final Map<Integer, Integer> resultMap = diceDistributionSimulation.execute();
        return DiceDistributionSimulationResponse.ok(resultMap);
    }
}
