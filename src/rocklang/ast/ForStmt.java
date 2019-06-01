package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class ForStmt extends ASTList {
    public ForStmt(AST[] children) {
        super(children);
    }

    public AST init() {
        return child(0);
    }

    public AST condition() {
        return child(1);
    }

    public AST action() {
        return child(2);
    }

    public AST body() {
        return child(3);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        Environment e = new Environment(env);
        Rock result = RockNil.NIL;
        AST init = init();
        AST cond = condition();
        AST action = action();
        AST body = body();
        for (init.value(e, null); cond.value(e, null).getBoolean(); action.value(e, null)) {
            result = body.value(e, null);
        }
        return result;
    }

    @Override
    public AST simplify() {
        return new ForStmt(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("for (");
        init().format(fs);
        fs.print("; ");
        condition().format(fs);
        fs.print("; ");
        action().format(fs);
        fs.print(") ");
        body().format(fs);
    }
}
