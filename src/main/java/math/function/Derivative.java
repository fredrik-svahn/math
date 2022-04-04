package math.function;

import java.util.function.Consumer;

public class Derivative {

    private final Expression expression;
    private final String variable;

    public Derivative(Expression e, String variable) {
        this.expression = e;
        this.variable = variable;
    }

    public void withExpression(Consumer<Expression> consumer) {

    }
}
