package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceDistributionSimulation {
    private final int minimumDiceSides, diceSides, numberOfDice, numberOfRolls;
    private NumberGenerator numberGenerator;

    public DiceDistributionSimulation(int minimumDiceSides, int diceSides, int numberOfDice, int numberOfRolls, NumberGenerator numberGenerator) {
        this.minimumDiceSides = minimumDiceSides;
        this.diceSides = diceSides;
        this.numberOfDice = numberOfDice;
        this.numberOfRolls = numberOfRolls;
        this.numberGenerator = numberGenerator;
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

    public Map<Integer, Integer> execute() {
        final List<Dice> dice = diceList(numberOfDice, minimumDiceSides, diceSides, numberGenerator);
        Map<Integer, Integer> resultMap = initialiseResultMap(numberOfDice, diceSides);
        IntStream.range(0, numberOfRolls)
                .mapToObj(l -> new DiceRollExecution(dice).execute())
                .forEach(i -> resultMap.merge(i, 1, Integer::sum));
        return resultMap;
    }

    private List<Dice> diceList(int numberOfDice, int minimumDiceSides, int diceSides, NumberGenerator numberGenerator) {
        return IntStream.range(0, numberOfDice)
                .mapToObj(i -> new Dice(
                        minimumDiceSides,
                        diceSides, numberGenerator))
                .collect(Collectors.toList());
    }

    private Map<Integer, Integer> initialiseResultMap(int numberOfDice, int diceSides) {
        final int maxSumValue = numberOfDice * diceSides;
        return IntStream.range(numberOfDice, maxSumValue + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> 0));
    }
}
