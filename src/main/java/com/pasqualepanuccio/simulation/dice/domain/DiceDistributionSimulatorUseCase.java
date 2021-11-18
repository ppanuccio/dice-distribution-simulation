package com.pasqualepanuccio.simulation.dice.domain;

public class DiceDistributionSimulatorUseCase {

    private final Simulator diceDistributionSimulator;
    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository;

    public DiceDistributionSimulatorUseCase(Simulator diceDistributionSimulator, DiceDistributionSimulationRepository diceDistributionSimulationRepository) {
        this.diceDistributionSimulator = diceDistributionSimulator;
        this.diceDistributionSimulationRepository = diceDistributionSimulationRepository;
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

            final DiceDistributionSimulation diceDistributionSimulation = diceDistributionSimulator.simulate(
                    request.getMinimumDiceSides(), request.getNumberOfDice(), request.getDiceSides(), request.getNumberOfRolls());

            diceDistributionSimulationRepository.save(diceDistributionSimulation);
            return DiceDistributionSimulationResponse.ok(diceDistributionSimulation.getDiceDistribution());
        } catch (Exception e) {
            return DiceDistributionSimulationResponse.ko(e.getMessage());
        }
    }
}
