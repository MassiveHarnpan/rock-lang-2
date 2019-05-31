package rocklang.tokenizer;

import rocklang.token.Token;
import rocklang.token.TokenType;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentRange;
import rocklang.util.DocumentStream;

public class IntegerTokenizer extends Tokenizer {
    @Override
    protected Token doTokenize(DocumentStream ds, DocumentIndex startIndex) {
        if (!Character.isDigit(ds.next())) {
            return null;
        }
        while (ds.hasMore() && Character.isDigit(ds.next())) {
            ds.read();
        }
        DocumentRange range = ds.rangeFrom(startIndex);
        String literal = range.cut();
        int value = Integer.valueOf(literal);
        return new Token(TokenType.INTEGER, range, literal, value);
    }
}
