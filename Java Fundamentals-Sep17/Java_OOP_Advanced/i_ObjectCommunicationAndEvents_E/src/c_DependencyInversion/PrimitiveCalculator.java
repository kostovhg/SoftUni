package c_DependencyInversion;


public class PrimitiveCalculator {

    private Strategy strategy;

    public PrimitiveCalculator() {
        this.strategy = new AdditionStrategy();
    }

    public void changeStrategy(char operator) {
        switch (operator) {
            case '+':
                this.strategy = new AdditionStrategy();
                break;
            case '-':
                this.strategy = new SubtractionStrategy();
                break;
            case '*':
                this.strategy = new MultiplicationStrategy();
                break;
            case '/':
                this.strategy = new DivisingStrategy();
                break;
        }
    }

    public int performCalculation(int firstOperand, int secondOperand) {
        return this.strategy.Calculate(firstOperand, secondOperand);
    }
}
