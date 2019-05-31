package rocklang.parser;

import rocklang.ast.AST;
import rocklang.token.TokenStream;

import java.util.List;

public class MaybeParser extends NonTerminateParser {

    private Parser parser;

    public MaybeParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    protected boolean doParse(TokenStream ts, List<AST> scope) {
        parser.parse(ts, scope);
        return true;
    }
}
