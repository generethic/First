package HomeWork3;

public class CalculatorWithMathExtends extends CalculatorWithOperator implements ICalculator {
    @Override
    public double exponentiation(double a, int b) {
        return Math.pow(a,b);
    }

    @Override
    public double rootExtraction(int a) {
        return Math.sqrt(a);
    }

    @Override
    public double changeByModule(double a) {
        return Math.abs(a);
    }
}
