package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pasqualepanuccio.simulation.dice.domain.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${mongodb.connection}")
    private String connectionString;

    @Value("${mongodb.db-name}")
    private String dbName;

    @Bean
    public DiceDistributionSimulationRepository mongoDiceDistributionSimulationRepository(MongoTemplate mongoTemplate) {
        return new MongoDBDiceDistributionSimulationRepository(mongoTemplate);
    }

    @Bean
    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    @Bean
    public Simulator diceDistributionSimulator(NumberGenerator numberGenerator) {
        return new DiceDistributionSimulator(numberGenerator);
    }

    @Bean
    public DiceDistributionSimulatorUseCase diceDistributionSimulatorUseCase(
            Simulator diceDistributionSimulator,
            DiceDistributionSimulationRepository diceDistributionSimulationRepository
    ) {
        return new DiceDistributionSimulatorUseCase(diceDistributionSimulator, diceDistributionSimulationRepository);
    }

    @Bean
    public DiceRelativeDistributionSummaryUseCase getTotalNumberOfSimulationsAndRollsUseCase(
            DiceDistributionSimulationRepository diceDistributionSimulationRepository) {
        return new DiceRelativeDistributionSummaryUseCase(diceDistributionSimulationRepository);
    }

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), this.dbName);
    }
}
