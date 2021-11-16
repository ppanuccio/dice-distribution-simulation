package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceDistributionSimulatorUseCase {

    public DiceDistributionSimulationResponse run(DiceDistributionSimulationRequest request) {
        final List<Dice> dice = diceList(request);
        final Map<Integer, Integer> resultMap = executeSimulation(request, dice);
        return new DiceDistributionSimulationResponse(resultMap);
    }

    private Map<Integer, Integer> executeSimulation(DiceDistributionSimulationRequest request, List<Dice> dice) {
        Map<Integer, Integer> resultMap = initialiseResultMap(request.getNumberOfDice(), request.getDiceSides());
        IntStream.range(0, request.getNumberOfExecution())
                .mapToObj(l -> new DiceRollExecution(dice).execute())
                .forEach(i -> resultMap.merge(i, 1, Integer::sum));
        return resultMap;
    }

    private List<Dice> diceList(DiceDistributionSimulationRequest request) {
        return IntStream.range(0, request.getNumberOfDice())
                .mapToObj(i -> new Dice(
                        request.getMinimumDiceSides(),
                        request.getDiceSides()))
                .collect(Collectors.toList());
    }

    private Map<Integer, Integer> initialiseResultMap(int numberOfDice, int diceSides) {
        final int maxSumValue = numberOfDice * diceSides;
        return IntStream.range(numberOfDice, maxSumValue + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> 0));
    }
}
