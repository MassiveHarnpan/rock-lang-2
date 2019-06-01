package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.FormatStream;

import java.io.IOException;

public class ClosedExpression extends ASTList {
    public ClosedExpression(AST[] children) {
        super(children);
    }

    public AST expression() {
        return child(0);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        return expression().value(env, null);
    }

    @Override
    public AST simplify() {
        return new ClosedExpression(new AST[] {expression().simplify()});
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("(");
        expression().format(fs);
        fs.print(")");
    }
}
