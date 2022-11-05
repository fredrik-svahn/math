package math.function;

import java.util.stream.Stream;

public class Subtraction extends Expression {

    private final Expression a;
    private final Expression b;

    public Subtraction(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Expression evaluate() {
        if(a.asDouble() == null) return this;
        if(b.asDouble() == null) return this;

        return new RealConstant(a.asDouble() - b.asDouble());
    }

    @Override
    public Expression bind(String variableName, Expression value) {
        return new Subtraction(
                a.bind(variableName, value),
                b.bind(variableName, value)
        );
    }

    @Override
    protected Stream<Variable> variables() {
        return Stream.concat(a.variables(), b.variables());
    }

    @Override
    public Expression clone() {
        return new Subtraction(
                a.clone(),
                b.clone()
        );
    }

    @Override
    public String toString() {
        return String.format("(%s - %s)", a, b);
    }
}
