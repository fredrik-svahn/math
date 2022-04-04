package math.function;

import java.util.function.Consumer;

public abstract class Expression {
    public abstract void withConstantValue(Consumer<Double> consumer);
    public abstract void bindAll(String x, double i);
    public abstract void withDerivative(String variable, Consumer<Expression> consumer);
    public abstract String toString();
}
