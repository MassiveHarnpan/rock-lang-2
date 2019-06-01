package rocklang.parser;

import rocklang.ast.AST;
import rocklang.ast.ASTFactory;
import rocklang.ast.ASTList;
import rocklang.token.Token;
import rocklang.token.TokenStream;

import java.util.List;

public abstract class Parser {

    public static boolean debug = true;
    public static int layer = 0;

    protected String name = getClass().getSimpleName();

    protected ASTFactory factory;
    protected boolean asAst = false;

    public Parser asAST(Class<? extends  AST> clazz) {
        this.factory = ASTFactory.of(clazz);
        this.asAst = true;
        return this;
    }

    public Parser asAST() {
        this.asAst = true;
        return this;
    }

    public Parser named(String name) {
        this.name = name;
        return this;
    }

    protected AST create(List<AST> res) {
        return this.factory.make(res);
    }

    protected AST create(Token token) {
        return this.factory.make(token);
    }



    public abstract AST parse(TokenStream ts);

    abstract boolean parse(TokenStream ts, List<AST> scope);




}
