package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.DiceApplication;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRepository;
import com.pasqualepanuccio.simulation.dice.domain.TotalByDiceNumberAndDiceSides;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = {DiceApplication.class, DiceDistributionSimulationControllerTest.TestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DiceDistributionSimulationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void return_dice_distribution_simulation() {
        ParameterizedTypeReference<java.util.Map<Integer, Integer>> responseType = new ParameterizedTypeReference<>() {
        };
        final ResponseEntity<java.util.Map<Integer, Integer>> response = restTemplate.exchange(
                "/dice-distribution-simulation?numberOfDice=1&diceSides=6&numberOfRolls=10", HttpMethod.GET, null, responseType);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void return_bad_request_on_missing_parameter() {
        final ResponseEntity<String> response = restTemplate.exchange(
                "/dice-distribution-simulation?diceSides=3&numberOfRolls=10", HttpMethod.GET, null, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void return_bad_request_on_error_validation() {
        final ResponseEntity<String> response = restTemplate.exchange(
                "/dice-distribution-simulation?numberOfDice=1&diceSides=3&numberOfRolls=10", HttpMethod.GET, null, String.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void return_dice_relative_distribution() {
        final ResponseEntity<TotalByDiceNumberAndDiceSides> response = restTemplate.exchange(
                "/dice-relative-distribution", HttpMethod.GET, null, TotalByDiceNumberAndDiceSides.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Configuration
    public static class TestConfig {
        @Bean
        @Primary
        public DiceDistributionSimulationRepository inMemoryDiceDistributionSimulationRepository() {
            return new InMemoryDiceDistributionRepository();
        }
    }
}