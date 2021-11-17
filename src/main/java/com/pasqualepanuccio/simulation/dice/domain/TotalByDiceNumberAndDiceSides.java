package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;

public class TotalByDiceNumberAndDiceSides {

    private final List<DiceDistributionSimulation> diceDistributionSimulations;

    public TotalByDiceNumberAndDiceSides(List<DiceDistributionSimulation> diceDistributionSimulations) {
        this.diceDistributionSimulations = diceDistributionSimulations;
    }

    public List<DiceDistributionSimulation> getDiceDistributionSimulations() {
        return diceDistributionSimulations;
    }
}
