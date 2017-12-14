package c_DependencyInversion.models.strategies;

import c_DependencyInversion.contracts.Strategy;

public class MultiplicationStrategy implements Strategy {

    @Override
    public int Calculate(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }
}
