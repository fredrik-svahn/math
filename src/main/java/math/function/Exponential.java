package math.function;

import java.util.stream.Stream;

public class Exponential extends Expression {

    private final Expression base;
    private final Expression exponent;

    public Exponential(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public Expression evaluate() {
        if(base.asDouble() == null) return this;
        if(exponent.asDouble() == null) return this;

        return new RealConstant(Math.pow(base.asDouble(), exponent.asDouble()));
    }

    @Override
    public Expression bind(String variableName, Expression value) {
        return new Exponential(
                base.bind(variableName, value),
                exponent.bind(variableName, value)
        );
    }

    @Override
    protected Stream<Variable> variables() {
        return Stream.concat(
                base.variables(),
                exponent.variables()
        );
    }

    @Override
    public Expression clone() {
        return new Exponential(
                base.clone(),
                exponent.clone()
        );
    }

    @Override
    public String toString() {
        return String.format("(%s ^ %s)", base, exponent);
    }
}
