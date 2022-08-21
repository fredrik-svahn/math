package function;

import math.function.Expression;
import math.function.RealConstant;
import math.function.Variable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionTest {

    @Test
    void name() {

    }

    @Test
    void testEvaluatingAConstantReturnsTheConstant() {
        int someValue = 42;
        RealConstant constant = new RealConstant(someValue);
        Expression result = constant.evaluate();

        assertTrue(result instanceof RealConstant);
        assertEquals(someValue, ((RealConstant) result).value);
    }

    @Test
    void testEvaluatingAVariableReturnsTheVariable() {
        Expression variable = new Variable("x");
        assertEquals(variable, variable.evaluate());
    }

    @Test
    void testBindingAConstantValueToAVariableAndThenEvaluatingItReturnsTheBoundValue() {
        Expression variable = new Variable("x");
        double someValue = 123;
        variable.bind("x", new RealConstant(someValue));
        assertEquals(new RealConstant(someValue), variable.evaluate());
    }

    @Test
    void testTwoVariablesWithTheSameNameAreTheSame() {
        Expression x1 = new Variable("x");
        Expression x2 = new Variable("x");

        assertEquals(x1, x2);
    }

    @Test
    void testBindingToAllVariablesInASumEvaluatesProperly() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Expression sum = new Sum(x, y);
        sum.bind("x", new RealConstant(123));
        sum.bind("y", new RealConstant(456));

        assertEquals(123+456, sum.asDouble());
    }
}
