package HomeWork3;

public class CalculatorDecoratorMain {
    public static void main(String[] args) {
        double a = 4.1;
        int b = 15;
        int c = 7;
        double d = 28;
        int e = 5;
        int f = 2;
        ICalculator calculator = new CalculatorWithCounterDecorator(new CalculatorWithMemoryDecorator(new CalculatorWithMathExtends()));
        calculator.addition(a,calculator.division(b,c));
        calculator.writeMemory();
        System.out.printf("%.4f",calculator.addition(calculator.exponentiation(d/e,f),calculator.getMemory()));
        System.out.println("");
        System.out.println(calculator.getCountOperation());
    }
}
