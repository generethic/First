package HomeWork3;

public interface ICalculator {
    double addition(double a,double b);
    double subtraction(double a, double b);
    double multiplication(double a, double b);
    double division(double a, double b);
    double exponentiation(double a, int b);
    double rootExtraction(int a);
    double changeByModule(double a);
    long getCountOperation();
    void writeMemory();
    double getMemory();
}