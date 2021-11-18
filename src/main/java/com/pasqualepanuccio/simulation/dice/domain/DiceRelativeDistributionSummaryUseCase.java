package com.pasqualepanuccio.simulation.dice.domain;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiceRelativeDistributionSummaryUseCase {

    private final DiceDistributionSimulationRepository diceDistributionSimulationRepository;
    private final DiceRelativeDistribution diceRelativeDistribution;

    public DiceRelativeDistributionSummaryUseCase(DiceDistributionSimulationRepository diceDistributionSimulationRepository, DiceRelativeDistribution diceRelativeDistribution) {
        this.diceDistributionSimulationRepository = diceDistributionSimulationRepository;
        this.diceRelativeDistribution = diceRelativeDistribution;
    }

    public TotalByDiceNumberAndDiceSides run() {
        List<DiceDistributionSimulation> all = diceDistributionSimulationRepository.findAll();
        Map<Pair<Integer, Integer>, List<DiceDistributionSimulation>> grouped = all.stream()
                .collect(Collectors.groupingBy(d -> Pair.of(d.getNumberOfDice(), d.getDiceSides())));

        List<SumByDiceNumberAndDiceSides> sumByDiceNumberAndDiceSides = grouped.keySet().stream()
                .map(pair -> getSumByDiceNumberAndDiceSides(pair, grouped.get(pair)))
                .collect(Collectors.toUnmodifiableList());

        return new TotalByDiceNumberAndDiceSides(sumByDiceNumberAndDiceSides);
    }

    private SumByDiceNumberAndDiceSides getSumByDiceNumberAndDiceSides(Pair<Integer, Integer> pair, List<DiceDistributionSimulation> diceDistributionSimulations) {
        String aggregateKey = pair.toString();
        int numberOfSimulation = diceDistributionSimulations.size();
        int numberOfRolls = diceDistributionSimulations.stream()
                .map(DiceDistributionSimulation::getNumberOfRolls)
                .reduce(0, Integer::sum);
        final Map<Integer, Double> relativeDistribution = diceRelativeDistribution.relativeDistribution(diceDistributionSimulations, numberOfRolls);
        SumByDiceNumberAndDiceSides.Details details = new SumByDiceNumberAndDiceSides.Details(
                numberOfSimulation,
                numberOfRolls,
                relativeDistribution);
        return new SumByDiceNumberAndDiceSides(aggregateKey, details);
    }
}
