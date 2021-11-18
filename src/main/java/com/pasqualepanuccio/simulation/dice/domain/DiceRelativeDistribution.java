package com.pasqualepanuccio.simulation.dice.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiceRelativeDistribution {

    public DiceRelativeDistribution() {
    }

    public Map<Integer, Integer> absoluteDistribution(List<DiceDistributionSimulation> diceDistributionSimulations) {
        return diceDistributionSimulations.stream()
                .map(DiceDistributionSimulation::getDiceDistribution)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));
    }

    public Map<Integer, Double> relativeDistribution(List<DiceDistributionSimulation> diceDistributionSimulations, int totalRolls) {
        final Map<Integer, Integer> total = absoluteDistribution(diceDistributionSimulations);
        return total.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> makeRelative(v, totalRolls)));
    }

    private Double makeRelative(Map.Entry<Integer, Integer> v, int totalRolls) {
        final BigDecimal occurrences = BigDecimal.valueOf(v.getValue()).setScale(2);
        final BigDecimal total = BigDecimal.valueOf(totalRolls).setScale(2);
        final BigDecimal percentage = occurrences.divide(total).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal relative = percentage.multiply(BigDecimal.valueOf(100));
        return relative.doubleValue();
    }
}
