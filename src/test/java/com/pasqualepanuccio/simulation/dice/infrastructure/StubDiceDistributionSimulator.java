package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.DiceDistributionSimulation;
import com.pasqualepanuccio.simulation.dice.domain.Simulator;

import java.util.Map;

public class StubDiceDistributionSimulator implements Simulator {

    private final Map<Integer, Integer> stubbedMap;

    public StubDiceDistributionSimulator(Map<Integer, Integer> stubbedMap) {
        this.stubbedMap = stubbedMap;
    }

    @Override
    public DiceDistributionSimulation simulate(int minimumDiceSides, int numberOfDice, int diceSides, int numberOfRolls) {
        return new DiceDistributionSimulation(minimumDiceSides, diceSides, numberOfDice, numberOfRolls, stubbedMap);
    }
}
