package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class SumByDiceNumberAndDiceSides {

    private final String key;
    private final Details details;

    public SumByDiceNumberAndDiceSides(String key, Details details) {
        this.key = key;
        this.details = details;
    }

    public String getKey() {
        return key;
    }

    public Details getDetails() {
        return details;
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

    public static class Details {
        private final int numberOfSimulation;
        private final int numberOfRolls;
        private final Map<Integer, Double> diceRelativeDistribution;

        public Details(int numberOfSimulation, int numberOfRolls, Map<Integer, Double> diceRelativeDistribution) {
            this.numberOfSimulation = numberOfSimulation;
            this.numberOfRolls = numberOfRolls;
            this.diceRelativeDistribution = diceRelativeDistribution;
        }

        public int getNumberOfSimulation() {
            return numberOfSimulation;
        }

        public int getNumberOfRolls() {
            return numberOfRolls;
        }

        public Map<Integer, Double> getDiceRelativeDistribution() {
            return diceRelativeDistribution;
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
}
