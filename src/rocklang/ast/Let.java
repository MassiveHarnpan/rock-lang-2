package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class Let extends ASTList {
    public Let(AST[] children) {
        super(children);
    }

    public AST varName() {
        return child(0);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        return env.setLocal(varName().token().literal(), RockNil.NIL);
    }

    @Override
    public Rock assign(Environment env, Rock base, Rock value) throws RockException {
        return env.setLocal(varName().token().literal(), value);
    }

    @Override
    public AST simplify() {
        return new Let(simplifyASTList(children()));
    }


    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("let ");
        varName().format(fs);
    }
}
