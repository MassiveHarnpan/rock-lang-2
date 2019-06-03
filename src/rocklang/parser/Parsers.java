package rocklang.parser;

import rocklang.ast.*;
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

        Parser integer = integer().named("integer");
        Parser decimal = decimal().named("decimal");
        Parser string = string().named("string");
        Parser numebr = fork(integer, decimal).named("numebr");
        Parser variableName = new TokenParser(TokenType.NAME).asAST(VariableName.class).named("variableName");

        ForkParser literalValue = fork();

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
                .element(variableName)
                .splitter(",")
                .allowEmptyElement()
                .suffix(")")
                .build()
                .asAST(ASTList.class)
                .named("parameters");


        SequenceParser arrow = sequence();


        Parser suffix = fork(arguments).named("suffix");
        Parser closedExpression = sequence().skip("(").then(expr).skip(")").asAST(ClosedExpression.class).named("closedExpression");
        Parser closedPart = fork(arrow, closedExpression).named("closedPart");
        Parser multipart = sequence()
                .then(fork(closedPart, literalValue))
                .then(PatternParser.builder().element(suffix).build())
                .asAST(Multipart.class)
                .named("multipart");

//        Parser basicValue = fork(multipart).named("basicValue");
//
//        Parser base = fork(closedPart, basicValue).named("base");
        Parser powerExpression = PatternParser.builder()
                .element(multipart)
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
                .splitter("+", "-", "..")
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



        Parser let = sequence().skip("let").then(variableName).asAST(Let.class).named("let");

        ForkParser statement = fork();
        Parser blockStmt = PatternParser.builder()
                .prefix("{")
                .element(statement)
                .allowEmptyElement()
                .suffix("}")
                .build()
                .asAST(BlockStmt.class)
                .named("blockStmt");


        Parser ifStmt = sequence()
                .skip("if").then(expr).then(blockStmt)
                .maybe(sequence().skip("else").then(blockStmt).named("elseStmt"))
                .asAST(IfStmt.class)
                .named("ifStmt");

        Parser whileStmt = sequence().skip("while")
                .then(expr)
                .then(blockStmt)
                .asAST(WhileStmt.class)
                .named("whileStmt");

        Parser forStmt = sequence().skip("for").skip("(")
                .then(expr).skip(";")
                .then(expr).skip(";")
                .then(expr).skip(")")
                .then(blockStmt)
                .asAST(ForStmt.class)
                .named("forStmt");

        Parser funcDef = sequence()
                .skip("def")
                .then(variableName)
                .then(parameters)
                .then(blockStmt)
                .asAST(FuncDef.class)
                .named("duncDef");

        Parser assign = sequence(fork(let, multipart)).skip("=").then(expr).asAST(Assign.class).named("assign");
        expr.or(assign).or(logicExpression).named("expr");

        Parser flowStatement = sequence(fork(expr, let)).skip(";").asAST(FlowStatement.class).named("flowStatement");

        statement.or(ifStmt).or(whileStmt).or(forStmt).or(flowStatement).named("statement");

        Parser arrowParameters = fork(parameters, sequence(variableName).asAST()).named("arrowParameters");
        Parser arrowBody = fork(blockStmt, expr).named("arrowBody");
        arrow
                .then(arrowParameters)
                .skip("=>")
                .then(arrowBody)
                .asAST(Arrow.class)
                .named("arrow");

        literalValue.or(decimal).or(integer).or(string).or(variableName).named("literalValue");


        Parser programStatement = fork(funcDef, statement).named("programStatement");

        Parser program = PatternParser.builder()
                .element(programStatement)
                .allowEmptyElement()
                .build()
                .asAST(Program.class)
                .named("program");


        return program;
    }




}
