package math.function;

import math.function.Expression;

import java.util.function.Consumer;

public class Sum extends Expression {

    private final Expression c1;
    private final Expression c2;

    public Sum(Expression c1, Expression c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void withConstantValue(Consumer<Double> consumer) {
        c1.withConstantValue(v1 -> {
            c2.withConstantValue(v2 -> {
                consumer.accept(v1 + v2);
            });
        });
    }

    @Override
    public void bindAll(String x, double i) {
        c1.bindAll(x, i);
        c2.bindAll(x, i);
    }

    @Override
    public void withDerivative(String variable, Consumer<Expression> consumer) {
        c1.withDerivative(variable, d1 -> {

            c2.withDerivative(variable, d2 -> {
                consumer.accept(new Sum(d1, d2));
            });
        });
    }

    @Override
    public String toString() {
        return "(" + c1 + "+" + c2 + ")";
    }
}
