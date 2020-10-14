package HomeWork3;

public abstract class Decorator implements ICalculator {
    ICalculator calculator;

    public Decorator(ICalculator calculator) {
        this.calculator = calculator;
    }
}

