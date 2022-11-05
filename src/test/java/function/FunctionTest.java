package function;

import math.function.Expression;
import math.function.ExpressionFactory;
import math.function.Sum;
import math.function.Variable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FunctionTest {

    @ParameterizedTest
    @MethodSource("evaluationResults")
    void expressionEquivalentAfterEvaluating(Expression expectedExpression, Expression secondExpression) {
        assertEquals(expectedExpression, secondExpression);
    }

    public static Stream<Arguments> evaluationResults() {
        ExpressionFactory factory = new ExpressionFactory();

        factory.createFromString("x + 3");
        return Stream.of(
                Arguments.of(new Variable("x"), new Variable("x")),
                Arguments.of(new Sum(new Variable("x"), new Variable("y")), new Sum(new Variable("y"), new Variable("x"))),
                Arguments.of(new Variable("x"), new Variable("x")),
                Arguments.of(new Variable("x"), new Variable("x")),
                Arguments.of(new Variable("x"), new Variable("x")),
                Arguments.of(new Variable("x"), new Variable("x")),
                Arguments.of(new Variable("x"), new Variable("x")),
                Arguments.of(new Variable("x"), new Variable("x"))
        );
    }

}

