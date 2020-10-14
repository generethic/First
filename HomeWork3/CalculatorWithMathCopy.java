package HomeWork3;

public class CalculatorWithMathCopy implements ICalculator {
    public double addition(double a,double b) {
        return a + b;
    }
    public double subtraction(double a, double b) {
        return a - b;
    }
    public double multiplication(double a, double b) {
        return a * b;
    }
    public double division(double a, double b) {
        return a / b;
    }
    public double exponentiation(double a, int b) {
        return Math.pow(a,b);
    }
    public double rootExtraction(int a) {
        return Math.sqrt(a);
    }
    public double changeByModule(double a) {
        return Math.abs(a);
    }

    @Override
    public long getCountOperation() {
        return 0;
    }

    @Override
    public double getMemory() {
        return 0;
    }

    @Override
    public void writeMemory() {

    }
}
