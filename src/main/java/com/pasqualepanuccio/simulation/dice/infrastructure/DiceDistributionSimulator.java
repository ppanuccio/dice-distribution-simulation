package com.pasqualepanuccio.simulation.dice.infrastructure;

import com.pasqualepanuccio.simulation.dice.domain.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceDistributionSimulator implements Simulator {

    private final NumberGenerator numberGenerator;

    public DiceDistributionSimulator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public DiceDistributionSimulation simulate(int minimumDiceSides, int numberOfDice, int diceSides, int numberOfRolls) {
        Map<Integer, Integer> diceDistribution = initialiseDiceDistribution(numberOfDice, diceSides);
        List<Dice> dice = diceList(numberOfDice, minimumDiceSides, diceSides);
        IntStream.range(0, numberOfRolls)
                .mapToObj(l -> new DiceRollExecution(dice).execute())
                .forEach(i -> diceDistribution.merge(i, 1, Integer::sum));
        return new DiceDistributionSimulation(
                minimumDiceSides,
                diceSides,
                numberOfDice,
                numberOfRolls,
                diceDistribution);
    }

    private Map<Integer, Integer> initialiseDiceDistribution(int numberOfDice, int diceSides) {
        final int maxSumValue = numberOfDice * diceSides;
        return IntStream.range(numberOfDice, maxSumValue + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> 0));
    }

    private List<Dice> diceList(int numberOfDice, int minimumDiceSides, int diceSides) {
        return IntStream.range(0, numberOfDice)
                .mapToObj(i -> new Dice(
                        minimumDiceSides,
                        diceSides, numberGenerator))
                .collect(Collectors.toList());
    }
}
