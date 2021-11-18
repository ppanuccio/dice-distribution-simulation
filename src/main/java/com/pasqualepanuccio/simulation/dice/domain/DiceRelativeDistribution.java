package com.pasqualepanuccio.simulation.dice.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiceRelativeDistribution {

    private final List<DiceDistributionSimulation> diceDistributionSimulations;
    private final int totalRolls;

    public DiceRelativeDistribution(List<DiceDistributionSimulation> diceDistributionSimulations, int totalRolls) {
        this.diceDistributionSimulations = diceDistributionSimulations;
        this.totalRolls = totalRolls;
    }

    public List<DiceDistributionSimulation> getDiceDistributionSimulations() {
        return diceDistributionSimulations;
    }

    public int getTotalRolls() {
        return totalRolls;
    }

    public Map<Integer, Integer> absoluteDistribution() {
        return diceDistributionSimulations.stream()
                .map(DiceDistributionSimulation::getDiceDistribution)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));
    }

    public Map<Integer, Double> relativeDistribution() {
        final Map<Integer, Integer> total = absoluteDistribution();
        return total.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> makeRelative(v)));
    }

    private Double makeRelative(Map.Entry<Integer, Integer> v) {
        final BigDecimal occurrences = BigDecimal.valueOf(v.getValue()).setScale(2);
        final BigDecimal total = BigDecimal.valueOf(totalRolls).setScale(2);
        final BigDecimal percentage = occurrences.divide(total).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal relative = percentage.multiply(BigDecimal.valueOf(100));
        return relative.doubleValue();
    }
}
