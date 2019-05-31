package rocklang.ast;

import rocklang.util.FormatStream;

import java.io.IOException;

public class Prefix extends ASTList {
    public Prefix(AST[] children) {
        super(children);
    }

    public AST prefix() {
        return child(0);
    }

    public AST base() {
        return child(1);
    }


    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print(prefix().token().literal());
        base().format(fs);
    }
}
