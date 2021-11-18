package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.*;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public DiceDistributionSimulationRepository diceDistributionSimulationRepository() {
        return new InMemoryDiceDistributionRepository();
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
}
