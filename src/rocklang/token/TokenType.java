package rocklang.token;

public enum TokenType {

    INTEGER("integer"),
    FLOAT("float"),
    STRING("string"),
    NAME("name"),
    IDENTIFIER("identifier"),
    COMMENT("comment"),
    ;

    public final String name;

    TokenType(String name) {
        this.name = name;
    }
}
