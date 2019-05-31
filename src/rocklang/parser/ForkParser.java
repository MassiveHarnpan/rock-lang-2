package rocklang.parser;

import rocklang.ast.AST;
import rocklang.token.TokenStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForkParser extends NonTerminateParser {

    List<Parser> parsers = new ArrayList<>();

    public ForkParser(Parser... parsers) {
        this.parsers.addAll(Arrays.asList(parsers));
    }

    public ForkParser or(Parser parser) {
        this.parsers.add(parser);
        return this;
    }

    @Override
    protected boolean doParse(TokenStream ts, List<AST> scope) {
        for (Parser parser : parsers) {
            if (parser.parse(ts, scope)) {
                return true;
            }
        }
        return false;
    }
}
