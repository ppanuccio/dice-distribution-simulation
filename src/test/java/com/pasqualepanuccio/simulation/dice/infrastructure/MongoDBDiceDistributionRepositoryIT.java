package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class MongoDBDiceDistributionRepositoryIT {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void insert() {
        DiceDistributionSimulation diceDistributionSimulation = new DiceDistributionSimulation(1, 6, 1, 1, null, Collections.emptyMap());

        mongoTemplate.insert(diceDistributionSimulation, MongoDBDiceDistributionSimulationRepository.DICE_DISTRIBUTION_SIMULATION_COLLECTION_NAME);

        assertThat(mongoTemplate.findAll(DiceDistributionSimulation.class, MongoDBDiceDistributionSimulationRepository.DICE_DISTRIBUTION_SIMULATION_COLLECTION_NAME)).extracting("diceSides")
                .containsOnly(6);
    }
}