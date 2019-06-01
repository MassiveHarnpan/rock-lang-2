package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class Multipart extends ASTList {
    public Multipart(AST[] children) {
        super(children);
    }

    public AST base() {
        return child(0);
    }


    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        Rock actBase = base().value(env, null);
        for (int i = 1; i < childCount(); i++) {
            actBase = child(i).value(env, actBase);
        }
        return actBase;
    }

    @Override
    public AST simplify() {
        if (childCount() == 1) {
            return child(0).simplify();
        }
        return new Multipart(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        for (int i = 0; i < childCount(); i++) {
            child(i).format(fs);
        }
    }
}
