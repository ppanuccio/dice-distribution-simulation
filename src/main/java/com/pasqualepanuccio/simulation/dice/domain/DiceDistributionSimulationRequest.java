package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DiceDistributionSimulationRequest {

    private final int minimumDiceSides;
    private final int diceSides;
    private final int numberOfDice;
    private final int numberOfRolls;

    public DiceDistributionSimulationRequest(int numberOfMinimumDiceSides, int numberOfDiceSides, int numberOfDice, int numberOfRolls) {
        this.minimumDiceSides = numberOfMinimumDiceSides;
        this.diceSides = numberOfDiceSides;
        this.numberOfDice = numberOfDice;
        this.numberOfRolls = numberOfRolls;
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

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
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
