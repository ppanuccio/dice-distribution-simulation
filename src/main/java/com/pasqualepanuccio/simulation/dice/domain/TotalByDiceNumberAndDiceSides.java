package com.pasqualepanuccio.simulation.dice.domain;

import java.util.List;

public class TotalByDiceNumberAndDiceSides {

    private final List<SumByDiceNumberAndDiceSides> sumByDiceNumberAndDiceSides;

    public TotalByDiceNumberAndDiceSides(List<SumByDiceNumberAndDiceSides> sumByDiceNumberAndDiceSides) {
        this.sumByDiceNumberAndDiceSides = sumByDiceNumberAndDiceSides;
    }

    public List<SumByDiceNumberAndDiceSides> getSumByDiceNumberAndDiceSides() {
        return sumByDiceNumberAndDiceSides;
    }
}
