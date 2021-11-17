package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationRequest;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulationResponse;
import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulatorUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DiceDistributionSimulationController {

    public static final int NUMBER_OF_MINIMUM_DICE_SIDES = 1;
    private final DiceDistributionSimulatorUseCase diceDistributionSimulatorUseCase;

    @Autowired
    public DiceDistributionSimulationController(DiceDistributionSimulatorUseCase diceDistributionSimulatorUseCase) {
        this.diceDistributionSimulatorUseCase = diceDistributionSimulatorUseCase;
    }

    @GetMapping(value = "/dice-distribution-simulation")
    public ResponseEntity diceDistributionSimulation(
            @RequestParam String numberOfDice,
            @RequestParam String diceSides,
            @RequestParam String numberOfRolls) {
        DiceDistributionSimulationRequest request = new DiceDistributionSimulationRequest(
                NUMBER_OF_MINIMUM_DICE_SIDES,
                Integer.parseInt(diceSides),
                Integer.parseInt(numberOfDice),
                Integer.parseInt(numberOfRolls));
        final DiceDistributionSimulationResponse simulationResponse = diceDistributionSimulatorUseCase.run(request);
        if (simulationResponse.isOk()) {
            return ResponseEntity.ok(simulationResponse.getMap());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, simulationResponse.getErrorMessage());
        }
    }
}
