package rocklang.token;

import rocklang.runtime.Rock;
import rocklang.util.DocumentRange;

public class Token {


    private TokenType type;
    private DocumentRange range;
    private String literal;
    private Rock value;


    public Token(TokenType type, DocumentRange range, String literal, Rock value) {
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

    public Rock value() {
        return this.value;
    }

    public String literal() {
        return literal;
    }


    @Override
    public String toString() {
        return type.name + ": " + value + ": " + literal;
    }
}
