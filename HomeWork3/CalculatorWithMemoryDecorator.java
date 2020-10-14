package HomeWork3;

public class CalculatorWithMemoryDecorator extends Decorator {
    private double memoryBuffer;
    private double memory;
    public CalculatorWithMemoryDecorator(ICalculator calculator) {
        super(calculator);
    }

    ICalculator getCalculator() {
        return calculator;
    }
    public void writeMemory() {
        this.memory = memoryBuffer;
    }
    public double getMemory() {
        double tmp = memory;
        memory = 0;
        return tmp;
    }

    @Override
    public double addition(double a, double b) {
        memoryBuffer =  calculator.addition(a, b);
        return memoryBuffer;
    }

    @Override
    public double subtraction(double a, double b) {
        memoryBuffer =  calculator.subtraction(a, b);
        return memoryBuffer;
    }

    @Override
    public double multiplication(double a, double b) {
        memoryBuffer =  calculator.multiplication(a, b);
        return memoryBuffer;
    }

    @Override
    public double division(double a, double b) {
        memoryBuffer =  calculator.division(a, b);
        return memoryBuffer;
    }

    @Override
    public double exponentiation(double a, int b) {
        memoryBuffer =  calculator.exponentiation(a, b);
        return memoryBuffer;
    }

    @Override
    public double rootExtraction(int a) {
        memoryBuffer =  calculator.rootExtraction(a);
        return memoryBuffer;
    }

    @Override
    public double changeByModule(double a) {
        memoryBuffer =  calculator.changeByModule(a);
        return memoryBuffer;
    }

    @Override
    public long getCountOperation() {
        return calculator.getCountOperation();
    }
}
