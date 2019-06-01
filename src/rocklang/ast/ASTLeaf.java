package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
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
    public Rock value(Environment env, Rock base) {
        return token.value();
    }

    public Rock assign(Environment env, Rock base, Rock value) throws RockException {
        throw new RockException("cannot assign to a literal");
    }

    @Override
    public AST simplify() {
        return this;
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print(token.literal());
    }

    @Override
    public String toString() {
        return token.literal();
    }
}
