package rocklang.parser;

import rocklang.ast.AST;
import rocklang.token.TokenStream;

import java.util.ArrayList;
import java.util.List;

public class SkipParser extends NonTerminateParser {

    private Parser parser;

    public SkipParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    protected boolean doParse(TokenStream ts, List<AST> scope) {
        List<AST> res = new ArrayList<>();
        return parser.parse(ts, res);
    }
}
