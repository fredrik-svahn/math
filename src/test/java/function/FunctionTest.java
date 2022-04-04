package function;

import math.function.*;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {

    public static final double DOUBLE_PARAM = 10;
    public static final double DOUBLE_VALUE = 5;

    private double value;
    private boolean wasCalled;


    @Test
    void constantExpressionReturnsValue() {
        Constant expr = new Constant(DOUBLE_VALUE);
        expr.withConstantValue(capture());
        assertEquals(DOUBLE_VALUE, value);
    }

    @Test
    void sumOfConstantsReturnsConstant() {
        Expression c1 = new Constant(3);
        Expression c2 = new Constant(8);
        Expression sum = new Sum(c1, c2);

        sum.withConstantValue(capture());
        assertEquals(11, value);
    }

    @Test
    void sumWithNonConstantDoesNotReturnConstantValue() {
        Expression v = new Variable("x");
        Expression c = new Constant(3);
        Expression sum = new Sum(v, c);

        sum.withConstantValue(called());
        assertFalse(wasCalled);
    }

    @Test
    void productOfConstantsIsConstant() {
        Expression c1 = new Constant(4);
        Expression c2 = new Constant(8);
        Expression product = new Product(c1, c2);

        product.withConstantValue(capture());
        assertEquals(32, value);
    }

    @Test
    void boundVariableHasConstantValue() {
        Variable var = new Variable("x");
        var.bind(30);
        var.withConstantValue(capture());
        assertEquals(30, value);
    }

    @Test
    void canBindAllVariablesWithName() {
        Variable v1 = new Variable("x");
        Variable v2 = new Variable("x");
        Expression sum = new Sum(v1, v2);
        sum.bindAll("x", 5);
        sum.withConstantValue(capture());
        assertEquals(10, value);
    }

    @Test
    void derivativeOfVariableIsOne() {
        Expression e  = new Variable("x");
        e.withDerivative("x", ex -> ex.withConstantValue(capture()));
        assertEquals(1, value);
    }

    @Test
    void derivativeOfConstantIsZero() {
        Expression e = new Constant(DOUBLE_VALUE);
        e.withDerivative("x", ex -> ex.withConstantValue(capture()));
        assertEquals(0, value);
    }

    @Test
    void derivativeInRelationToDifferentVariableIsConstant() {
        Expression e = new Variable("y");
        e.withDerivative("x", expression -> expression.withConstantValue(capture()));
        assertEquals(0, value);
    }

    @Test
    void addingAndSubtractingTheSameValueDoesNotChangeResult() {
        Expression e1 = new Sum(new Variable("x"), new Constant(-1));
        Expression e2 = new Sum(new Variable("x"), new Sum(new Constant(1), new Constant(-2)));

        assertTrue(equivalentExpressions(e1, e2, "x"));
    }

    @Test
    void multiplyingAndDividingByTheSameValueDoesNotChangeResult() {
        Expression e1 = new Variable("x");
        Expression e2 = new Fraction(new Product(new Variable("x"), new Constant(2)), new Constant(2));

        assertTrue(equivalentExpressions(e1, e2, "x"));
    }

    @Test
    void derivativeOfSumIsSumOfDerivatives() {
        Expression e1 = new Sum(new Variable("x"), new Product(new Variable("x"), new Constant(3)));
        Expression e2 = new Sum(new Constant(1), new Constant(3));

        e1.withDerivative("x", d -> {
            System.out.println(d);

            d.withConstantValue(v -> System.out.println(v));

            assertTrue(equivalentExpressions(d, e2));
            wasCalled = true;
        });


        assertTrue(wasCalled);
    }

    private boolean equivalentExpressions(Expression e1, Expression e2, String... variables) {
        if(variables.length == 0) {
            boolean[] result = new boolean[]{false};
            e1.withConstantValue(v1 -> {
                e2.withConstantValue(v2 -> {
                    if(v1.equals(v2)) result[0] = true;
                });
            });

            return result[0];
        }

        for (String variable : variables) {
            for (int i = -1000; i < 1000; i++) {
                e1.bindAll(variable, i);
                e2.bindAll(variable, i);

                boolean[] result = new boolean[]{ false };
                e1.withConstantValue(v1 -> {
                    e2.withConstantValue(v2 -> {
                        if(v1.equals(v2)) result[0] = true;
                    });
                });
                if(!result[0]) return false;
            }
        }

        return true;
    }

    private Consumer<Double> capture() {
        value = Integer.MIN_VALUE;
        return d -> value = d;
    }
    private Consumer<Double> called() {
        wasCalled = false;
        return d -> wasCalled = true;
    }
}
