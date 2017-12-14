package c_DependencyInversion.contracts;

public interface Calculator {

    void changeStrategy(char operator);

    int performCalculation(int firstOperand, int secondOperand);
}
