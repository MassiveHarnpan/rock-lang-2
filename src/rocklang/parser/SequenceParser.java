package rocklang.parser;

import rocklang.ast.AST;
import rocklang.token.TokenStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequenceParser extends NonTerminateParser {

    private List<Parser> parsers = new ArrayList<>();


    public SequenceParser(Parser... parsers) {
        this.parsers.addAll(Arrays.asList(parsers));
    }

    @Override
    protected boolean doParse(TokenStream ts, List<AST> scope) {
        for (Parser parser : parsers) {
            if (!parser.parse(ts, scope)) {
                return false;
            }
        }
        return true;
    }

    public SequenceParser then(Parser parser) {
        parsers.add(parser);
        return this;
    }

    public SequenceParser then(String... allowedIdentifiers) {
        parsers.add(new TokenParser(allowedIdentifiers));
        return this;
    }

    public SequenceParser skip(Parser parser) {
        parsers.add(new SkipParser(parser));
        return this;
    }

    public SequenceParser skip(String... allowedIdentifiers) {
        parsers.add(new SkipParser(new TokenParser(allowedIdentifiers)));
        return this;
    }

    public SequenceParser fork(Parser... forkParsers) {
        parsers.add(new ForkParser(forkParsers));
        return this;
    }

    public Parser maybe(Parser parser) {
        parsers.add(new MaybeParser(parser));
        return this;
    }

    public Parser maybe(String... identifiers) {
        parsers.add(new MaybeParser(new TokenParser(identifiers)));
        return this;
    }



}
