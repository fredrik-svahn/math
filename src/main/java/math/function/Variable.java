package math.function;

import java.util.function.Consumer;

public class Variable extends Expression {

    private double value;
    private boolean isBound;
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public Variable() {

    }

    @Override
    public void withConstantValue(Consumer<Double> consumer) {
        if (!isBound) return;
        consumer.accept(value);
    }

    @Override
    public void bindAll(String x, double i) {
        if (name.equals(x)) {
            bind(i);
        }
    }

    @Override
    public void withDerivative(String variable, Consumer<Expression> consumer) {
        if (variable.equals(name)) consumer.accept(new Constant(1));
        else consumer.accept(new Constant(0));
    }

    @Override
    public String toString() {
        return name;
    }

    public void bind(double value) {
        this.value = value;
        isBound = true;
    }
}
