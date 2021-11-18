package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pasqualepanuccio.simulation.dice.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
    
    @Bean
    public DiceDistributionSimulationRepository mongoDiceDistributionSimulationRepository(MongoTemplate mongoTemplate) {
        return new MongoDBDiceDistributionSimulationRepository(mongoTemplate);
    }

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public DiceDistributionSimulatorUseCase diceDistributionSimulatorUseCase(
            DiceDistributionSimulationRepository diceDistributionSimulationRepository,
            NumberGenerator numberGenerator
    ) {
        return new DiceDistributionSimulatorUseCase(diceDistributionSimulationRepository, numberGenerator);
    }

    @Bean
    public GetTotalNumberOfSimulationsAndRollsUseCase getTotalNumberOfSimulationsAndRollsUseCase(
            DiceDistributionSimulationRepository diceDistributionSimulationRepository) {
        return new GetTotalNumberOfSimulationsAndRollsUseCase(diceDistributionSimulationRepository);
    }

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/diceDistribution");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "diceDistribution");
    }
}
