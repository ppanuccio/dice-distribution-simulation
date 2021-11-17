package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRepository;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulatorUseCase;
import com.pasqualepanuccio.simulation.dice.domain.NumberGenerator;
import com.pasqualepanuccio.simulation.dice.domain.RandomNumberGenerator;
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

}
