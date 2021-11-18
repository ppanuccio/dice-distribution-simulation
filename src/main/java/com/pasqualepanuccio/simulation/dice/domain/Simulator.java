package com.pasqualepanuccio.simulation.dice.domain;

public interface Simulator {

    DiceDistributionSimulation simulate(int minimumDiceSides, int numberOfDice, int diceSides, int numberOfRolls);
}
