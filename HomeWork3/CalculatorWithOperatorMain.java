package HomeWork3;

public class CalculatorWithOperatorMain  {
    public static void main(String[] args) {
        double a = 4.1;
        int b = 15;
        int c = 7;
        double d = 28;
        int e = 5;
        int f = 2;
        CalculatorWithOperator calculator = new CalculatorWithOperator();
        double x = calculator.division(b,c);
        double y = calculator.exponentiation((d / e), f);
        double q = calculator.addition(x, y);
        double z = calculator.addition(a, q);
        System.out.format("%.4f",z);

    }
}
