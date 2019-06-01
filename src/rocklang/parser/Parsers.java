package rocklang.parser;

import javafx.scene.control.ProgressBar;
import rocklang.ast.*;
import rocklang.token.TokenType;

import javax.sound.midi.Sequence;

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

        Parser integer = integer().named("integer");
        Parser decimal = decimal().named("decimal");
        Parser string = string().named("string");
        Parser numebr = fork(integer, decimal).named("numebr");
        Parser variableName = new TokenParser(TokenType.NAME).asAST(VariableName.class).named("variableName");
        Parser literalValue = fork(decimal, integer, string, variableName).named("literalValue");

        ForkParser expr = fork();
        Parser arguments = PatternParser.builder()
                .prefix("(")
                .element(expr)
                .splitter(",")
                .allowEmptyElement()
                .suffix(")")
                .build()
                .asAST(Arguments.class)
                .named("arguments");

        Parser parameters = PatternParser.builder()
                .prefix("(")
                .element(string)
                .splitter(",")
                .allowEmptyElement()
                .suffix(")")
                .build()
                .asAST(ASTList.class)
                .named("parameters");



        Parser suffix = fork(arguments).named("suffix");
        Parser closedExpression = sequence().skip("(").then(expr).skip(")").asAST(ClosedExpression.class).named("closedExpression");
        Parser multipart = sequence()
                .then(fork(closedExpression, literalValue))
                .then(PatternParser.builder().element(suffix).build())
                .asAST(Multipart.class)
                .named("multipart");

        Parser basicValue = fork(multipart).named("basicValue");



        Parser base = fork(closedExpression, basicValue).named("base");
        Parser powerExpression = PatternParser.builder()
                .element(base)
                .splitter("^")
                .atLeastOneElement()
                .recordSplitter()
                .build()
                .asAST(Expression.class)
                .named("powerExpression");
        Parser term = PatternParser.builder()
                .element(powerExpression)
                .splitter("*", "/", "%")
                .atLeastOneElement()
                .recordSplitter()
                .build()
                .asAST(Expression.class)
                .named("term");
        Parser prefix = sequence().then("+", "-", "!").then(term).asAST(Prefix.class).named("prefix");
        Parser numberingExpression = PatternParser.builder()
                .element(fork(prefix, term))
                .splitter("+", "-")
                .atLeastOneElement()
                .recordSplitter()
                .build()
                .asAST(Expression.class)
                .named("numberingExpression");
        Parser compareExpression = PatternParser.builder()
                .element(numberingExpression)
                .splitter(">", "<", ">=", "<=", "==", "!=")
                .atLeastOneElement()
                .recordSplitter()
                .build()
                .asAST(Expression.class)
                .named("compareExpression");
        Parser logicExpression = PatternParser.builder()
                .element(compareExpression)
                .splitter("&&", "||")
                .atLeastOneElement()
                .recordSplitter()
                .build()
                .asAST(Expression.class)
                .named("logicExpression");
        expr.or(closedExpression).or(logicExpression).named("expr");


        Parser program = PatternParser.builder()
                .element(expr)
                .splitter(";")
                .allowEmptyElement()
                .build()
                .asAST(Program.class)
                .named("program");





        Parser blockStmt = PatternParser.builder()
                .prefix("{")
                .element(literalValue)
                .splitter(";")
                .allowEmptyElement()
                .suffix("}")
                .build()
                .asAST(BlockStmt.class)
                .named("blockStmt");


        Parser ifStmt = sequence()
                .skip("if").then(literalValue).then(blockStmt)
                .maybe(sequence().skip("else").then(blockStmt).named("elseStmt"))
                .asAST(IfStmt.class)
                .named("ifStmt");



        return program;
    }




}
