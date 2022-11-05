package math.function.parser;

import math.function.parser.token.*;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    private Map<String, Function<String, Token>> tokenPatterns = new HashMap<>();
    private List<Token> tokens;
    private int currentTokenIndex = -1;

    public Lexer(String input) {
        this.input = input;

        tokenPatterns.put("(\\+)", (group) -> new Plus());
        tokenPatterns.put("(\\-)", (group) -> new Minus());
        tokenPatterns.put("(\\*)", (group) -> new Asterisk());
        tokenPatterns.put("(\\/)", (group) -> new Slash());
        tokenPatterns.put("([0-9]+(.[0-9]+)?)", DecimalNumber::new);
        tokenPatterns.put("([A-Za-z]+)", Identifier::new);
        tokenPatterns.put("\\(", group -> new LParen());
        tokenPatterns.put("\\)", group -> new RParen());

    }

    public void tokenize() {
        tokens = new ArrayList<>();

        Pattern pattern = Pattern.compile(combinedPattern());
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String matched = matcher.group();
            Token token = tokenPatterns.keySet().stream().filter(matched::matches).findFirst().map(key -> tokenPatterns.get(key)).orElse(g -> new Invalid()).apply(matched);
            tokens.add(token);
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Token consumeNext(Class<? extends Token> type) {
        assertNext(type);
        return consumeNext();
    }

    public Token consumeNext() {
        Token token = peekNext();
        currentTokenIndex++;
        return token;
    }

    public void assertNext(Class<? extends Token> type) {
        assert peekNext().getClass().equals(type);
    }

    public Token peekNext() {
        return tokens.get(currentTokenIndex + 1);
    }

    public boolean nextIs(Class<? extends Token> type) {
        return peekNext().getClass().equals(type);
    }


    private String combinedPattern() {
        return String.join("|", tokenPatterns.keySet());
    }
}
