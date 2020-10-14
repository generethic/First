package HomeWork3;

public class CalculatorWithMathCopyMain {
    public static void main(String[] args) {
        double a = 4.1;
        int b = 15;
        int c = 7;
        double d = 28;
        int e = 5;
        int f = 2;
        CalculatorWithMathCopy calculator1 = new CalculatorWithMathCopy();
        double x = calculator1.division(b,c);
        double y = calculator1.exponentiation((d / e), f);
        double q = calculator1.addition(x, y);
        double z = calculator1.addition(a, q);
        System.out.format("%.4f",z);
    }
}
