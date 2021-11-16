package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.Map;

public class DiceDistributionSimulationResponse {

    private final Map<Integer, Integer> map;
    private String errorMessage;

    private DiceDistributionSimulationResponse(Map<Integer, Integer> map, String errorMessage) {
        this.map = map;
        this.errorMessage = errorMessage;
    }

    public static DiceDistributionSimulationResponse ok(Map<Integer, Integer> map) {
        return new DiceDistributionSimulationResponse(map, "");
    }

    public static DiceDistributionSimulationResponse ko(String errorMessage) {
        return new DiceDistributionSimulationResponse(Collections.emptyMap(), errorMessage);
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isOk() {
        return StringUtils.isBlank(errorMessage);
    }

    public boolean isKo() {
        return !isOk();
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
