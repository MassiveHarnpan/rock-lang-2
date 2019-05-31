package rocklang.ast;

import rocklang.runtime.Enviroument;
import rocklang.token.Token;
import rocklang.util.FormatStream;

import java.io.IOException;

public class ASTLeaf implements AST {

    private Token token;

    public ASTLeaf(Token token) {
        this.token = token;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public AST[] children() {
        return null;
    }

    @Override
    public AST child(int index) {
        return null;
    }

    @Override
    public int childCount() {
        return 0;
    }

    @Override
    public Token token() {
        return token;
    }

    @Override
    public Object value(Enviroument env, Object base) {
        return token.value();
    }

    @Override
    public Object assign(Enviroument env, Object base) {
        return null;
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print(String.valueOf(token.value()));
    }

    @Override
    public String toString() {
        return token.literal();
    }
}
