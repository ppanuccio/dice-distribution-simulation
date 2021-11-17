package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;

public interface DiceDistributionSimulationRepository {

    void save(DiceDistributionSimulation diceDistributionSimulation);

    List<DiceDistributionSimulation> findAll();

}
