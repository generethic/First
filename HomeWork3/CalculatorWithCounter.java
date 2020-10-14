package HomeWork3;

public class CalculatorWithCounter implements ICalculator {
    private long countOperation;
    public long getCountOperation() {
        return countOperation;
    }

    @Override
    public double getMemory() {
        return 0;
    }

    @Override
    public void writeMemory() {

    }

    CalculatorWithOperator calculatorWithOperator;
    CalculatorWithMathCopy calculatorWithMathCopy;
    CalculatorWithMathExtends calculatorWithMathExtends;

    CalculatorWithCounter(CalculatorWithOperator calculatorWithOperator) {
        this.calculatorWithOperator = calculatorWithOperator;
    }
    CalculatorWithCounter(CalculatorWithMathCopy calculatorWithMathCopy) {
        this.calculatorWithMathCopy = calculatorWithMathCopy;
    }
    CalculatorWithCounter(CalculatorWithMathExtends calculatorWithMathExtends) {
        this.calculatorWithMathExtends= calculatorWithMathExtends;
    }

    public double multiplication(double a, double b) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.multiplication(a, b);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.multiplication(a, b);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.multiplication(a, b);
        }
        return 0;
    }

    public double division(double a, double b) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.division(a, b);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.division(a, b);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.division(a, b);
        }
        return 0;
    }
    public double addition(double a, double b) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.addition(a, b);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.addition(a, b);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.addition(a, b);
        }
        return 0;
    }
    public double subtraction(double a, double b) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.subtraction(a, b);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.subtraction(a, b);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.subtraction(a, b);
        }
        return 0;
    }
    public double exponentiation(double a, int b) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.exponentiation(a, b);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.exponentiation(a, b);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.exponentiation(a, b);
        }
        return 0;
    }
    public double rootExtraction(int a) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.rootExtraction(a);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.rootExtraction(a);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.rootExtraction(a);
        }
        return 0;
    }
    public double changeByModule(double a) {
        countOperation++;
        if (calculatorWithOperator != null)
        {
            return calculatorWithOperator.changeByModule(a);
        }
        else if (calculatorWithMathCopy != null)
        {
            return calculatorWithMathCopy.changeByModule(a);
        }
        else if (calculatorWithMathExtends != null)
        {
            return calculatorWithMathExtends.changeByModule(a);
        }
        return 0;
    }
}