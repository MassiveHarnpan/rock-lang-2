package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class IfStmt extends ASTList {
    public IfStmt(AST[] children) {
        super(children);
    }

    public AST condition() {
        return child(0);
    }

    public AST thenStmt() {
        return child(1);
    }

    public AST elseStmt() {
        return childCount() >= 3 ? child(2) : null;
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        if (condition().value(env, base).getBoolean()) {
            return thenStmt().value(env, null);
        } else if (childCount() >= 3) {
            return elseStmt().value(env, null);
        }
        return RockNil.NIL;
    }

    @Override
    public AST simplify() {
        return new IfStmt(simplifyASTList(children()));
    }


    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("if ");
        condition().format(fs);
        fs.print(" ");
        thenStmt().format(fs);
        if (childCount() >= 3) {
            fs.print(" else ");
            elseStmt().format(fs);
        }
    }
}
