package math.function;

import math.function.parser.ExpressionParser;

public class ExpressionFactory {
    public Expression createFromString(String string) {
        return new ExpressionParser(string).parse();
    }
}
