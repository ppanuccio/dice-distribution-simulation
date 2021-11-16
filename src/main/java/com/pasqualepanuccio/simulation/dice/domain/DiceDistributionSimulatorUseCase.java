package com.pasqualepanuccio.simulation.dice.domain;

import java.util.Map;

public class DiceDistributionSimulatorUseCase {

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
                request.getNumberOfRolls());
        final Map<Integer, Integer> resultMap = diceDistributionSimulation.execute();
        return DiceDistributionSimulationResponse.ok(resultMap);
    }
}
