package rocklang.ast;

import rocklang.runtime.Enviroument;
import rocklang.token.Token;

public class VariableName extends ASTLeaf {
    public VariableName(Token token) {
        super(token);
    }

    @Override
    public Object value(Enviroument env, Object base) {
        return null;
    }

    @Override
    public Object assign(Enviroument env, Object base) {
        return null;
    }
}
