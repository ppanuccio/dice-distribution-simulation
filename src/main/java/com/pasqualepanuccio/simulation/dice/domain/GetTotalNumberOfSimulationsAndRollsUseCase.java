package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;

public class GetTotalNumberOfSimulationsAndRollsUseCase {

    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository;

    public GetTotalNumberOfSimulationsAndRollsUseCase(DiceDistributionSimulationRepository diceDistributionSimulationRepository) {
        this.diceDistributionSimulationRepository = diceDistributionSimulationRepository;
    }

    public TotalByDiceNumberAndDiceSides run() {

        List<DiceDistributionSimulation> diceDistributionSimulations = diceDistributionSimulationRepository.findAll();
        return new TotalByDiceNumberAndDiceSides(diceDistributionSimulations);
    }
}
