package HomeWork3;

public class CalculatorWithMemory implements ICalculator {
    ICalculator iCalculator;
    private double memoryBuffer;
    private double memory;
    private long countOperation;

    public void writeMemory() {
        this.memory = memoryBuffer;
    }

    public long getCountOperation() {
        return countOperation;
    }

    public double getMemory() {
        double tmp = memory;
        memory = 0;
        return tmp;
    }

    public CalculatorWithMemory(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }

    public double multiplication(double a, double b) {
        countOperation++;

        memoryBuffer = iCalculator.multiplication(a,b);

        return memoryBuffer;
    }

    public double division(double a, double b) {
        countOperation++;

        memoryBuffer = iCalculator.division(a,b);

        return memoryBuffer;
    }
    public double addition(double a, double b) {
        countOperation++;

        memoryBuffer = iCalculator.addition(a,b);

        return memoryBuffer;
    }
    public double subtraction(double a, double b) {
        countOperation++;

        memoryBuffer = iCalculator.subtraction(a,b);

        return memoryBuffer;
    }
    public double exponentiation(double a, int b) {
        countOperation++;

        memoryBuffer = iCalculator.exponentiation(a,b);

        return memoryBuffer;
    }
    public double rootExtraction(int a) {
        countOperation++;

        memoryBuffer = iCalculator.rootExtraction(a);

        return memoryBuffer;
    }
    public double changeByModule(double a) {
        countOperation++;

        memoryBuffer = changeByModule(a);

        return memoryBuffer;
    }
}
