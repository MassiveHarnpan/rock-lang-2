package rocklang.runtime;

public enum RockType {

    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean"),
    NIL("nil"),
    MAP("map"),
    ARRAY("array"),
    FUNCTION("function"),
    CLASS("class"),
    CONSTRUCTOR("constructor"),
    ;

    public final String name;

    RockType(String name) {
        this.name = name;
    }
}
