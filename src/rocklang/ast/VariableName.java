package rocklang.ast;

import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.token.Token;

public class VariableName extends ASTLeaf {
    public VariableName(Token token) {
        super(token);
    }

    public String name() {
        return token().literal();
    }

    @Override
    public Rock value(Environment env, Rock base) {
        String name = name();
        return env.has(name) ? env.get(name) : RockNil.NIL;
    }

    @Override
    public Rock assign(Environment env, Rock base, Rock value) {
        String name = name();
        return env.set(name, value);
    }
}
