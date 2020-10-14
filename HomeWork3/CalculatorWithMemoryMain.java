package HomeWork3;

public class CalculatorWithMemoryMain {
    public static void main(String[] args) {
        double a = 4.1;
        int b = 15;
        int c = 7;
        double d = 28;
        int e = 5;
        int f = 2;
        CalculatorWithMemory calculatorWithMemory = new CalculatorWithMemory(new CalculatorWithOperator());
        calculatorWithMemory.exponentiation(d/e,f);
        calculatorWithMemory.writeMemory();
        calculatorWithMemory.addition(a,calculatorWithMemory.getMemory());
        calculatorWithMemory.writeMemory();
        System.out.format("%.4f",calculatorWithMemory.getMemory()+calculatorWithMemory.division(b,c));
    }
}
