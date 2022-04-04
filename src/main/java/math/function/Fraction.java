package math.function;

import math.function.Expression;

import java.util.function.Consumer;

public class Fraction extends Expression {

    private final Expression e1;
    private final Expression e2;

    public Fraction(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void withConstantValue(Consumer<Double> consumer) {
        e1.withConstantValue(v1 -> {
            e2.withConstantValue(v2 -> {
                consumer.accept(v1/v2);
            });
        });
    }

    @Override
    public void bindAll(String x, double i) {
        e1.bindAll(x, i);
        e2.bindAll(x, i);
    }

    @Override
    public void withDerivative(String variable, Consumer<Expression> consumer) {

    }

    @Override
    public String toString() {
        return null;
    }
}
