package c_DependencyInversion;

public class MultiplicationStrategy implements Strategy {

    @Override
    public int Calculate(int firstOperand, int secondOperand) {
        return firstOperand * secondOperand;
    }
}
