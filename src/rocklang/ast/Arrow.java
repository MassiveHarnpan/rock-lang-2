package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockFunction;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class Arrow extends ASTList {
    public Arrow(AST[] children) {
        super(children);
    }

    public AST parameters() {
        return child(0);
    }

    public AST body() {
        return child(1);
    }


    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        AST parameters = parameters();
        String[] params = new String[parameters.childCount()];
        for (int i = 0; i < parameters.childCount(); i++) {
            params[i] = parameters.child(i).token().literal();
        }
        return new RockFunction(env, params, body());
    }

    @Override
    public AST simplify() {
        return new Arrow(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        AST params = parameters();
        if (params.childCount() == 1) {
            params.child(0).format(fs);
        } else {
            fs.print("(");
            for (int i = 0; i < params.childCount(); i++) {
                params.child(i).format(fs);
                if (i != params.childCount() - 1) {
                    fs.print(", ");
                }
            }
            fs.print(")");
        }

        fs.print(" => ");
        body().format(fs);
    }
}
