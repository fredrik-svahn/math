package function;

import math.function.Expression;
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

    public Stream<Arguments> evaluationResults() {
        return Stream.of(
                Arguments.of()
        );
    }

}

