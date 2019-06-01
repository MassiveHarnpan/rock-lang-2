package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.util.Calculation;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class Expression extends ASTList {
    public Expression(AST[] children) {
        super(children);
    }


    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        Rock former = child(0).value(env, null);
        for (int i = 1; i < childCount(); i += 2) {
            String operator = child(i).token().literal();
            Rock latter = child(i + 1).value(env, null);
            former = Calculation.calculate(former, operator, latter);
        }
        return former;
    }



    @Override
    public AST simplify() {
        if (childCount() == 1) {
            return child(0).simplify();
        }
        return new Expression(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        for (int i = 0; i < childCount(); i++) {
            child(i).format(fs);
            if (i != childCount() - 1) {
                fs.print(" ");
            }
        }
    }
}
