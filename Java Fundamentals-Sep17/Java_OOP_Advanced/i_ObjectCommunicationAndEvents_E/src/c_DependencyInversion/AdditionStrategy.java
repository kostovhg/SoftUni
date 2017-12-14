package c_DependencyInversion;

public class AdditionStrategy implements Strategy {

    @Override
    public int Calculate(int firstOperand, int secondOperand){
        return firstOperand + secondOperand;
    }
}
