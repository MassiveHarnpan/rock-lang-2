package rocklang.token;

import rocklang.tokenizer.*;
import rocklang.tokenizer.StringTokenizer;
import rocklang.util.Document;
import rocklang.util.DocumentStream;

import java.util.*;

public class TokenStream {

    private static Map<TokenType, Tokenizer> tokenizers = new HashMap<>();
    private static List<TokenType> typeListInOrder = new ArrayList<>();
    static {
        tokenizers.put(TokenType.COMMENT, new CommentTokenizer());
        tokenizers.put(TokenType.FLOAT, new FloatTokenizer());
        tokenizers.put(TokenType.INTEGER, new IntegerTokenizer());
        tokenizers.put(TokenType.IDENTIFIER, new IdentifierTokenizer());
        tokenizers.put(TokenType.NAME, new NameTokenizer());
        tokenizers.put(TokenType.STRING, new StringTokenizer());

        typeListInOrder.add(TokenType.COMMENT);
        typeListInOrder.add(TokenType.FLOAT);
        typeListInOrder.add(TokenType.INTEGER);
        typeListInOrder.add(TokenType.IDENTIFIER);
        typeListInOrder.add(TokenType.NAME);
        typeListInOrder.add(TokenType.STRING);
    }




    private Document document;
    private DocumentStream documentStream;
    private List<Token> cache = new ArrayList<>();
    private int pointer = 0;


    public TokenStream(Document document) {
        this.document = document;
        this.documentStream = new DocumentStream(document);
    }


    private Token safeGetToken(int index) {
        return index >= cache.size() ? null : cache.get(index);
    }


    public boolean hasMore() {
        fill(pointer);
        return pointer < cache.size();
    }

    public Token next() {
        fill(pointer);
        return safeGetToken(pointer);
    }

    public Token read() {
        fill(pointer);
        Token token = safeGetToken(pointer);
        pointer++;
        return token;
    }

    public void fill(int target) {
        if (target < cache.size()) {
            return;
        }
        Token token;
        do {
            token = tokenizeAllType();
            if (token != null && token.type() != TokenType.COMMENT) {
                cache.add(token);
            }
        } while (token != null && cache.size() <= target);
    }
//
//    public Token read(TokenType type) {
//        return tokenize(type);
//    }
//
//    public Token read(String... allowedIdentifiers) {
//        Set<String> ais = new HashSet<>(Arrays.asList(allowedIdentifiers));
//        Token token = tokenize(TokenType.IDENTIFIER);
//        return (token != null && ais.contains(token.literal())) ? token : null;
//    }


    private Token tokenizeAllType() {
        for (TokenType type : typeListInOrder) {
            Token token = tokenize(type);
            if (token != null) {
                return token;
            }
        }
        return null;
    }


    private Token tokenize(TokenType type) {
        Tokenizer tokenizer = tokenizers.get(type);
        Token token = tokenizer.tokenize(documentStream);
        return token;
    }

    public int checkPoint() {
        return pointer;
    }

    public void rollBack(int checkPoint) {
        pointer = checkPoint;
    }
}
