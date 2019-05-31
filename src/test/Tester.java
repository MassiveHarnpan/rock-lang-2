package test;

import rocklang.ast.AST;
import rocklang.ast.ASTList;
import rocklang.parser.Parser;
import rocklang.parser.Parsers;
import rocklang.parser.PatternParser;
import rocklang.parser.TokenParser;
import rocklang.token.Token;
import rocklang.token.TokenStream;
import rocklang.token.TokenType;
import rocklang.util.Document;
import rocklang.util.DocumentReaderUtil;
import rocklang.util.FormatStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Tester {


    static final File TEST_DIR = new File("tests");



    public static void main(String[] args) throws IOException {
        String testFileName = "test-2-parser-2.roc";
        File testFile = new File(TEST_DIR, testFileName);

        Document document = DocumentReaderUtil.read(testFileName, new FileReader(testFile));

        testParser2(document);


    }

    public static void testTokenizer(Document document) {
        TokenStream ts = new TokenStream(document);
        while (ts.hasMore()) {
            Token token = ts.read();
            if (token == null) {
                break;
            }
            System.out.println(token);
        }
    }

    public static void testParser1(Document document) {
        TokenStream ts = new TokenStream(document);
        Parser parser = PatternParser.builder()
                .prefix("[")
                .element(new TokenParser(TokenType.INTEGER))
                .splitter(",")
                .allowEmptyElement()
                .suffix("]")
                .build()
                .asAST(ASTList.class);
        AST ast = parser.parse(ts);
        showAST(ast, 0);
    }

    public static void testParser2(Document document) throws IOException {
        TokenStream ts = new TokenStream(document);
        Parser parser = Parsers.createProgramParser();
        AST ast = parser.parse(ts);
        if (ast != null) {
            ast = ast.simplify();
        }
        System.out.println("==showAST==");
        showAST(ast, 0);

        System.out.println("==AST.format()==");

        FormatStream fs = new FormatStream(System.out, "    ");
        if (ast != null) {
            ast.format(fs);
        }
    }

    public static void showAST(AST ast, int layer) {
        if (ast == null) {
            System.out.println("[Empty AST]");
            return;
        }
        if (ast.isLeaf()) {
            System.out.println(makeIndent(layer) + ast.token().literal());
        } else {
            for (int i = 0; i < ast.childCount(); i++) {
                showAST(ast.child(i), layer + 1);
            }
        }
    }

    public static String makeIndent(int layer) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < layer; i++) {
            if (i == layer - 1) {
                builder.append("|-- ");
            } else {
                builder.append("|---");
            }
        }
        return builder.toString();
    }



}
