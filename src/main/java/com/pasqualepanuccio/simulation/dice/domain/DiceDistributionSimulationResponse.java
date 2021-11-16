package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class DiceDistributionSimulationResponse {

    private final Map<Integer, Integer> map;

    public DiceDistributionSimulationResponse(Map<Integer, Integer> map) {
        this.map = map;
    }

    public Map<Integer, Integer> getMap() {
        return map;
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
