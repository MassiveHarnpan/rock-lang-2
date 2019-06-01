package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class WhileStmt extends ASTList {
    public WhileStmt(AST[] children) {
        super(children);
    }

    public AST condition() {
        return child(0);
    }

    public AST body() {
        return child(1);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        Rock result = RockNil.NIL;
        AST cond = condition();
        AST body = body();
        Environment e = new Environment(env);
        while (cond.value(env, null).getBoolean()) {
            result = body.value(e, null);
        }
        return result;
    }

    @Override
    public AST simplify() {
        return new WhileStmt(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("while ");
        condition().format(fs);
        fs.print(" ");
        body().format(fs);
    }
}
