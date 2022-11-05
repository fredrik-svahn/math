package math.function;

import java.util.stream.Stream;

public class Division extends Expression {

    private final Expression dividend;
    private final Expression divisor;

    public Division(Expression dividend, Expression divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    public Expression evaluate() {
        if (dividend.asDouble() == null) return this;
        if (divisor.asDouble() == null) return this;

        return new RealConstant(dividend.asDouble() / divisor.asDouble());
    }

    @Override
    public Expression bind(String variableName, Expression value) {
        return new Division(
                dividend.bind(variableName, value),
                divisor.bind(variableName, value)
        );
    }

    @Override
    protected Stream<Variable> variables() {
        return Stream.concat(dividend.variables(), divisor.variables());
    }

    @Override
    public Expression clone() {
        return new Division(dividend.clone(), divisor.clone());
    }

    @Override
    public String toString() {
        return String.format("(%s / %s)", dividend, divisor);
    }
}
