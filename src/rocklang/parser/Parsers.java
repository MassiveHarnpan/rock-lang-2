package rocklang.parser;

import rocklang.ast.BlockStmt;
import rocklang.ast.IfStmt;
import rocklang.token.TokenType;

public class Parsers {

    public static TokenParser integer() {
        return new TokenParser(TokenType.INTEGER);
    }

    public static TokenParser decimal() {
        return new TokenParser(TokenType.FLOAT);
    }

    public static TokenParser string() {
        return new TokenParser(TokenType.STRING);
    }



    public static ForkParser fork(Parser... parsers) {
        return new ForkParser(parsers);
    }

    public static SequenceParser sequence(Parser... parsers) {
        return new SequenceParser(parsers);
    }

    public static SkipParser skip(String... identifiers) {
        return new SkipParser(new TokenParser(identifiers));
    }

    public static SkipParser skip(Parser parser) {
        return new SkipParser(parser);
    }



    public static Parser createProgramParser() {

        Parser integer = integer();
        Parser decimal = decimal();
        Parser string = string();
        Parser numebr = fork(integer, decimal);
        Parser literalValue = fork(integer, decimal, string);

        Parser blockStmt = PatternParser.builder()
                .prefix("{")
                .element(literalValue)
                .splitter(";")
                .allowEmptyElement()
                .suffix("}")
                .build()
                .asAST(BlockStmt.class);


        Parser ifStmt = sequence()
                .skip("if").then(literalValue).then(blockStmt)
                .maybe(sequence().skip("else").then(blockStmt)).asAST(IfStmt.class);



        return ifStmt;
    }




}
