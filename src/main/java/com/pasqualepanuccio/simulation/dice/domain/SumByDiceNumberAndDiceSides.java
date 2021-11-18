package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        private final int numberOfSimulation, numberOfRolls;

        public Details(int numberOfSimulation, int numberOfRolls) {
            this.numberOfSimulation = numberOfSimulation;
            this.numberOfRolls = numberOfRolls;
        }

        public int getNumberOfSimulation() {
            return numberOfSimulation;
        }

        public int getNumberOfRolls() {
            return numberOfRolls;
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
