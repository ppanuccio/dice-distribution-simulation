package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulation;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDiceDistributionRepository implements DiceDistributionSimulationRepository {

    private final List<DiceDistributionSimulation> db = new ArrayList<>();

    @Override
    public void save(DiceDistributionSimulation diceDistributionSimulation) {
        db.add(diceDistributionSimulation);
    }

    @Override
    public List<DiceDistributionSimulation> findAll() {
        return db;
    }
}
