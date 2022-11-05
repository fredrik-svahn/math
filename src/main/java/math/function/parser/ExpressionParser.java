package math.function.parser;

import math.function.Expression;

public class ExpressionParser {
    private String input;

    public ExpressionParser(String input) {
        this.input = input;
    }

    public Expression parse() {
        Lexer lexer = new Lexer(input);
        lexer.tokenize();

        return new RecursiveParser(lexer).parse();
    }
}
