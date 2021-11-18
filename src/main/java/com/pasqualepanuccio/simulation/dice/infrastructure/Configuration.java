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
        ConnectionString connectionString = new ConnectionString(this.connectionString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), this.dbName);
    }
}
