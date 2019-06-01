package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockFunction;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class FuncDef extends ASTList {
    public FuncDef(AST[] children) {
        super(children);
    }

    public AST name() {
        return child(0);
    }

    public AST parameters() {
        return child(1);
    }

    public AST body() {
        return child(2);
    }

    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        String[] parameters = new String[parameters().childCount()];
        AST params = parameters();
        for (int i = 0; i < params.childCount(); i++) {
            parameters[i] = params.child(i).token().literal();
        }
        Rock function = new RockFunction(env, parameters, body());
        return env.set(name().token().literal(), function);
    }

    @Override
    public AST simplify() {
        return new FuncDef(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.newLine().print("def ");
        name().format(fs);
        fs.print("(");
        AST parameters = parameters();
        for (int i = 0; i < parameters.childCount(); i++) {
            parameters.child(i).format(fs);
            if (i != parameters.childCount() - 1) {
                fs.print(", ");
            }
        }
        fs.print(") ");
        body().format(fs);
    }
}
