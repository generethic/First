package HomeWork3;

public class CalculatorWithCounterDecorator extends Decorator {
    private long countOperation;
    public CalculatorWithCounterDecorator(ICalculator calculator) {
        super(calculator);
    }

    ICalculator getCalculator() {
        return calculator;
    }
    public long getCountOperation() {
        return countOperation;
    }

    @Override
    public void writeMemory() {
        calculator.writeMemory();
    }

    @Override
    public double getMemory() {
        return calculator.getMemory();
    }

    @Override
    public double addition(double a, double b) {
        countOperation++;
        return calculator.addition(a, b);
    }

    @Override
    public double subtraction(double a, double b) {
        countOperation++;
        return calculator.subtraction(a, b);
    }

    @Override
    public double multiplication(double a, double b) {
        countOperation++;
        return calculator.multiplication(a, b);
    }

    @Override
    public double division(double a, double b) {
        countOperation++;
        return calculator.division(a, b);
    }

    @Override
    public double exponentiation(double a, int b) {
        countOperation++;
        return calculator.exponentiation(a, b);
    }

    @Override
    public double rootExtraction(int a) {
        countOperation++;
        return calculator.rootExtraction(a);
    }

    @Override
    public double changeByModule(double a) {
        countOperation++;
        return calculator.changeByModule(a);
    }
}
