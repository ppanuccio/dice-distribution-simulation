package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class DiceDistributionSimulation {
    private final int minimumDiceSides;
    private final int diceSides;
    private final int numberOfDice;
    private int numberOfRolls;
    private Map<Integer, Integer> diceDistribution;

    public DiceDistributionSimulation(int minimumDiceSides, int diceSides, int numberOfDice, int numberOfRolls,
                                      Map<Integer, Integer> diceDistribution) {
        this.minimumDiceSides = minimumDiceSides;
        this.diceSides = diceSides;
        this.numberOfDice = numberOfDice;
        this.numberOfRolls = numberOfRolls;
        this.diceDistribution = diceDistribution;
    }

    public int getMinimumDiceSides() {
        return minimumDiceSides;
    }

    public int getDiceSides() {
        return diceSides;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public Map<Integer, Integer> getDiceDistribution() {
        return diceDistribution;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
