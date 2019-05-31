package rocklang.token;

import rocklang.util.DocumentRange;

import java.util.Objects;

public class Token {


    private TokenType type;
    private DocumentRange range;
    private String literal;
    private Object value;


    public Token(TokenType type, DocumentRange range, String literal, Object value) {
        this.type = type;
        this.range = range;
        this.literal = literal;
        this.value = value;
    }

    public DocumentRange range() {
        return range;
    }

    public TokenType type() {
        return type;
    }

    public Object value() {
        return value;
    }

    public int getInteger() {
        return (int) this.value;
    }

    public double getFloat() {
        return (double) this.value;
    }

    public String getString() {
        return (String) this.value;
    }

    public String literal() {
        return literal;
    }


    @Override
    public String toString() {
        return type.name + ": " + value + ": " + literal;
    }
}
