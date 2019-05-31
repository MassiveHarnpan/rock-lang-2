package rocklang.ast;

import rocklang.runtime.Enviroument;
import rocklang.util.FormatStream;

import java.io.IOException;

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
    public Object value(Enviroument env, Object base) {
        return super.value(env, base);
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
