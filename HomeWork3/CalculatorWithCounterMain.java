package HomeWork3;

public class CalculatorWithCounterMain {
    public static void main(String[] args) {
        double a = 4.1;
        int b = 15;
        int c = 7;
        double d = 28;
        int e = 5;
        int f = 2;
        CalculatorWithCounter calculator2 = new CalculatorWithCounter(new CalculatorWithMathExtends());
        double x = calculator2.division(b,c);
        double y = calculator2.exponentiation((d / e), f);
        double q = calculator2.addition(x, y);
        double z = calculator2.addition(a, q);
        System.out.format("%.4f",z);
        System.out.println("");
        System.out.println(calculator2.getCountOperation());
    }

}
