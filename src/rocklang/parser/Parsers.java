package rocklang.parser;

import rocklang.ast.BlockStmt;
import rocklang.ast.Expression;
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



        ForkParser expr = fork();
        Parser closedExpression = sequence().skip("(").then(expr).skip(")");

        Parser base = fork(closedExpression, literalValue);
        Parser powerExpression = PatternParser.builder()
                .element(base)
                .splitter("^")
                .atLeastOneElement()
                .build()
                .asAST(Expression.class);
        Parser term = PatternParser.builder()
                .element(powerExpression)
                .splitter("*", "/", "%")
                .atLeastOneElement()
                .build()
                .asAST(Expression.class);
        Parser prefix = sequence().then("+", "-", "!").then(term);
        Parser numberingExpression = PatternParser.builder()
                .element(fork(prefix, term))
                .splitter("+", "-")
                .atLeastOneElement()
                .build()
                .asAST(Expression.class);
        Parser compareExpression = PatternParser.builder()
                .element(numberingExpression)
                .splitter(">", "<", ">=", "<=", "==", "!=")
                .atLeastOneElement()
                .build()
                .asAST(Expression.class);
        Parser logicExpression = PatternParser.builder()
                .element(compareExpression)
                .splitter("&&", "||")
                .atLeastOneElement()
                .build()
                .asAST(Expression.class);
        expr.or(closedExpression).or(logicExpression);




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



        return expr;
    }




}
