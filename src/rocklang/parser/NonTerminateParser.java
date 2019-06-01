package rocklang.parser;

import rocklang.ast.AST;
import rocklang.ast.ASTFactory;
import rocklang.ast.ASTList;
import rocklang.token.TokenStream;
import rocklang.util.Utils;
import test.Tester;

import java.util.ArrayList;
import java.util.List;

public abstract class NonTerminateParser extends Parser {

    {
        this.factory = ASTFactory.of(ASTList.class);
    }



    public boolean parse(TokenStream ts, List<AST> scope) {

        if (debug) {
            System.out.print(Tester.makeIndent(layer));
            System.out.println("Start " + name + ": parsed " + scope);
            layer ++;
        }

        int checkPoint = ts.checkPoint();
        int scopePoint = scope.size();
        if (asAst) {
            AST ast = parse(ts);
            if (debug) {
                layer--;
                System.out.print(Tester.makeIndent(layer));
                System.out.println("End " + (ast == null ? "failed" : "succeed") + ": "+name + ": parsed " + scope);
            }
            if (ast != null) {
                scope.add(ast);
                return true;
            } else {
                Utils.cutList(scope, scopePoint);
                ts.rollBack(checkPoint);
                return false;
            }
        } else {
            boolean r = doParse(ts, scope);
            if (debug) {
                layer--;
                System.out.print(Tester.makeIndent(layer));
                System.out.println("End " + (!r ? "failed" : "succeed") + ": " + name + ": parsed " + scope);
            }
            if (r) {
                return true;
            } else {
                ts.rollBack(checkPoint);
                Utils.cutList(scope, scopePoint);
                return false;
            }
        }

    }

    public AST parse(TokenStream ts) {
        List<AST> res = new ArrayList<>();
        if (doParse(ts, res)) {
            return create(res);
        } else {
            return null;
        }

    }

    protected abstract boolean doParse(TokenStream ts, List<AST> scope);

}
