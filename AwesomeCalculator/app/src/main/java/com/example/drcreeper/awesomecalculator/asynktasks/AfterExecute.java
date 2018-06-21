package com.example.drcreeper.awesomecalculator.asynktasks;

import com.example.drcreeper.awesomecalculator.math.CalculatorHistory;

import java.util.List;

public interface AfterExecute {
    void setAfterQuery(List<CalculatorHistory> list);
}
