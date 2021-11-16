package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRequest;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationResponse;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulatorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiceDistributionSimulationController {

    @GetMapping(value = "/dice-distribution-simulation")
    public ResponseEntity diceDistributionSimulation(
            @RequestParam String numberOfDice,
            @RequestParam String diceSides,
            @RequestParam String numberOfRolls) {
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                1,
                Integer.parseInt(diceSides),
                Integer.parseInt(numberOfDice),
                Integer.parseInt(numberOfRolls));
        DiceDistributionSimulatorUseCase diceDistributionSimulatorUseCase = new DiceDistributionSimulatorUseCase();
        final DiceDistributionSimulationResponse simulationResponse = diceDistributionSimulatorUseCase.run(request);
        if (simulationResponse.isOk()) {
            return ResponseEntity.ok(simulationResponse.getMap());
        } else {
            return ResponseEntity.badRequest().body(simulationResponse.getErrorMessage());
        }
    }
}
