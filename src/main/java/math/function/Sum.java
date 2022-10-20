package math.function;

import java.util.stream.Stream;

public class Sum
        extends Expression {
    private Expression a;
    private Expression b;

    public Sum(Expression a,
               Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Expression evaluate() {
        if(
                a.evaluate() instanceof RealConstant
                && b.evaluate() instanceof RealConstant
        ) {
            double valueA = ((RealConstant) a.evaluate()).value;
            double valueB = ((RealConstant) b.evaluate()).value;

            return new RealConstant(valueA + valueB);
        }
        else {
            return this;
        }
    }

    @Override
    public Expression bind(String variableName,
                     Expression value) {
        return new Sum(
                a.bind(variableName, value),
                b.bind(variableName, value)
        );
    }

    @Override
    public Stream<Variable> variables() {
        return Stream.concat(a.variables(), b.variables());
    }

    @Override
    public Expression clone() {
        return new Sum(a.clone(), b.clone());
    }

    @Override
    public String toString() {
        return String.format("(%s + %s)", a.toString(), b.toString());
    }
}
