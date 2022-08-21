package function;

import math.function.Expression;
import math.function.RealConstant;

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
    public void bind(String variableName,
                     Expression value) {
        a.bind(variableName, value);
        b.bind(variableName, value);
    }
}
