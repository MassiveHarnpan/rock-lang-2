package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.Calculation;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

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
    public Rock value(Environment env, Rock base) throws RockException {
        return Calculation.calculate(null, prefix().token().literal(), base().value(env, null));
    }

    @Override
    public AST simplify() {
        return new Prefix(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print(prefix().token().literal());
        base().format(fs);
    }
}
