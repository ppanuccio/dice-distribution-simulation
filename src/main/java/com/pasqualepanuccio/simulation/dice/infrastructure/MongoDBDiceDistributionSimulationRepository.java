package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulation;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class MongoDBDiceDistributionSimulationRepository implements DiceDistributionSimulationRepository {

    public static final String DICE_DISTRIBUTION_SIMULATION_COLLECTION_NAME = "diceDistributionSimulation";
    private final MongoTemplate mongoTemplate;

    public MongoDBDiceDistributionSimulationRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(DiceDistributionSimulation diceDistributionSimulation) {
        mongoTemplate.insert(diceDistributionSimulation, DICE_DISTRIBUTION_SIMULATION_COLLECTION_NAME);
    }

    @Override
    public List<DiceDistributionSimulation> findAll() {
        return mongoTemplate.findAll(DiceDistributionSimulation.class, DICE_DISTRIBUTION_SIMULATION_COLLECTION_NAME);
    }
}
