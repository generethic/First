package HomeWork3;

public class CalculatorWithOperator implements ICalculator {
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
        if(a > 0) {
            for (int i = 1; i <= b; i++) {
                a *=  a;
                i++;
            }
        }
        return a;
    }
    public double rootExtraction(int a) {
        return Math.sqrt(a);
    }
    public double changeByModule(double a) {
        if(a>=0) {
            return a;
        }
        else
            return a*(-1);
    }

    @Override
    public long getCountOperation() {
        return this.getCountOperation();
    }

    @Override
    public void writeMemory() {
        this.writeMemory();
    }

    @Override
    public double getMemory() {
        return this.getMemory();
    }
}

