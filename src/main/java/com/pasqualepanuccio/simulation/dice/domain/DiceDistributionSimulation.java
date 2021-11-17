package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceDistributionSimulation {
    private final int minimumDiceSides;
    private final int diceSides;
    private final int numberOfDice;
    private int numberOfRolls;
    private NumberGenerator numberGenerator;
    private Map<Integer, Integer> diceDistribution;

    public DiceDistributionSimulation(int minimumDiceSides, int diceSides, int numberOfDice, int numberOfRolls,
                                      NumberGenerator numberGenerator, Map<Integer, Integer> diceDistribution) {
        this.minimumDiceSides = minimumDiceSides;
        this.diceSides = diceSides;
        this.numberOfDice = numberOfDice;
        this.numberOfRolls = numberOfRolls;
        this.numberGenerator = numberGenerator;
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

    public NumberGenerator getNumberGenerator() {
        return numberGenerator;
    }

    public Map<Integer, Integer> getDiceDistribution() {
        return diceDistribution;
    }

    public void addRolls(int numberOfRolls) {
        this.numberOfRolls += numberOfRolls;
    }

    public void execute() {
        final List<Dice> dice = diceList(numberOfDice, minimumDiceSides, diceSides, numberGenerator);
        IntStream.range(0, numberOfRolls)
                .mapToObj(l -> new DiceRollExecution(dice).execute())
                .forEach(i -> diceDistribution.merge(i, 1, Integer::sum));
    }

    private List<Dice> diceList(int numberOfDice, int minimumDiceSides, int diceSides, NumberGenerator numberGenerator) {
        return IntStream.range(0, numberOfDice)
                .mapToObj(i -> new Dice(
                        minimumDiceSides,
                        diceSides, numberGenerator))
                .collect(Collectors.toList());
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
