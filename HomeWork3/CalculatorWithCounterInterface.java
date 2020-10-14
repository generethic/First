package HomeWork3;

public class CalculatorWithCounterInterface {
    ICalculator iCalculator;

    CalculatorWithCounterInterface(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }
    public double multiplication(double a, double b) {
        return iCalculator.multiplication(a,b);
    }
    public double addition(double a,double b) {
        return iCalculator.addition(a,b);
    }
    public double subtraction(double a, double b) {
        return iCalculator.subtraction(a,b);
    }
    public double division(double a, double b) {
        return iCalculator.division(a,b);
    }
    public double exponentiation(double a, int b) {
        return iCalculator.exponentiation(a,b);
    }
    public double rootExtraction(int a) {
        return iCalculator.rootExtraction(a);
    }
    public double changeByModule(double a) {
        return iCalculator.changeByModule(a);
    }
}
