package rocklang.tokenizer;

import rocklang.token.Token;
import rocklang.token.TokenType;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentRange;
import rocklang.util.DocumentStream;

public class NameTokenizer extends Tokenizer {
    @Override
    protected Token doTokenize(DocumentStream ds, DocumentIndex startIndex) {
        char ch = ds.read();
        if (!isHead(ch)) {
            return null;
        }
        while (ds.hasMore() && isBody(ds.next())) {
            ds.read();
        }
        DocumentRange range = ds.rangeFrom(startIndex);
        String literal = ds.getDocument().range(range);
        return new Token(TokenType.NAME, range, literal, null);
    }


    private boolean isHead(char ch) {
        return ch == '_' || Character.isLetter(ch);
    }

    private boolean isBody(char ch) {
        return ch == '_' || Character.isLetterOrDigit(ch);
    }

}
