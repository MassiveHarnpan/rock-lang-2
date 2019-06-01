package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class Arguments extends ASTList {
    public Arguments(AST[] children) {
        super(children);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        Rock[] arguments = new Rock[childCount()];
        for (int i = 0; i < childCount(); i++) {
            arguments[i] = child(i).value(env, null);
        }
        return base.invoke(arguments);
    }

    @Override
    public AST simplify() {
        return new Arguments(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("(");
        fs.levelUp();
        for (int i = 0; i < childCount(); i++) {
            child(i).format(fs);
            if (i != childCount() - 1) {
                fs.print(", ");
            }
        }
        fs.levelDown();
        fs.print(")");
    }
}
