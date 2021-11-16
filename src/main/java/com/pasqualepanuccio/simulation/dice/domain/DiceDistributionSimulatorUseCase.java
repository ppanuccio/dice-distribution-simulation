package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceDistributionSimulatorUseCase {

    public DiceDistributionSimulationResponse run(DiceDistributionSimulationRequest request) {
        Map<Integer, Integer> resultMap = initialiseResultMap(request.getNumberOfDice(), request.getDiceSides());
        final List<Dice> dice = diceList(request);
        executeSimulation(request, resultMap, dice);
        return new DiceDistributionSimulationResponse(resultMap);
    }

    private void executeSimulation(DiceDistributionSimulationRequest request, Map<Integer, Integer> resultMap, List<Dice> dice) {
        IntStream.range(0, request.getNumberOfExecution())
                .mapToObj(l -> new DiceRollExecution(dice).execute())
                .forEach(i -> resultMap.merge(i, 1, Integer::sum));
    }

    private List<Dice> diceList(DiceDistributionSimulationRequest request) {
        return IntStream.range(0, request.getNumberOfDice())
                .mapToObj(i -> new Dice(
                        request.getMinimumDiceSides(),
                        request.getDiceSides()))
                .collect(Collectors.toList());
    }

    private Map<Integer, Integer> initialiseResultMap(int numberOfDice, int diceSides) {
        return IntStream.range(numberOfDice, (numberOfDice * diceSides) + 1)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> 0));
    }
}
