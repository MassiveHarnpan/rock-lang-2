package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.token.Token;
import rocklang.util.FormatStream;

import java.io.IOException;

public abstract class Native implements AST {
    @Override
    public boolean isLeaf() {
        return false;
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
        return null;
    }

    @Override
    public Rock assign(Environment env, Rock base, Rock value) throws RockException {
        return null;
    }

    @Override
    public AST simplify() {
        return this;
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("<Native code>");
    }

    @Override
    public String toString() {
        return "<Native code>";
    }
}
