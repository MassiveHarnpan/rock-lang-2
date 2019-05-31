package rocklang.ast;

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
    public void format(FormatStream fs) throws IOException {
        fs.print("(");
        expression().format(fs);
        fs.print(")");
    }
}
