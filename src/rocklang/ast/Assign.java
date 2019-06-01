package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class Assign extends ASTList {
    public Assign(AST[] children) {
        super(children);
    }

    public AST key() {
        return child(0);
    }

    public AST value() {
        return child(1);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        return key().assign(env, null, value().value(env, null));
    }


    @Override
    public AST simplify() {
        return new Assign(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        key().format(fs);
        fs.print(" = ");
        value().format(fs);
    }
}
