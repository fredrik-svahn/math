package math.function;

import java.util.function.Consumer;

public class Constant extends Expression {
    public double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public void withConstantValue(Consumer<Double> consumer) {
        consumer.accept(value);
    }

    @Override
    public void bindAll(String x, double i) { }

    @Override
    public void withDerivative(String variable, Consumer<Expression> consumer) {
        consumer.accept(new Constant(0));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
