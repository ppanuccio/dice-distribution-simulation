package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulation;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRepository;

import java.util.List;

public class DatabaseDiceDistributionRepository implements DiceDistributionSimulationRepository {

    @Override
    public void save(DiceDistributionSimulation diceDistributionSimulation) {
    }

    @Override
    public List<DiceDistributionSimulation> findAll() {
        return null;
    }
}
