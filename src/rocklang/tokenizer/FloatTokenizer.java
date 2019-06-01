package rocklang.tokenizer;

import rocklang.runtime.RockNumber;
import rocklang.token.Token;
import rocklang.token.TokenType;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentRange;
import rocklang.util.DocumentStream;

public class FloatTokenizer extends Tokenizer {
    @Override
    protected Token doTokenize(DocumentStream ds, DocumentIndex startIndex) {
        while (ds.hasMore() && Character.isDigit(ds.next())) {
            ds.read();
        }
        if (!ds.hasMore() || ds.read() != '.') {
            return null;
        }
        if (!ds.hasMore() || !Character.isDigit(ds.next())) {
            return null;
        }
        while (ds.hasMore() && Character.isDigit(ds.next())) {
            ds.read();
        }

        DocumentRange range = ds.rangeFrom(startIndex);
        String literal = range.cut();
        double value = Double.valueOf(literal.startsWith(".") ? "0" + literal : literal);
        return new Token(TokenType.FLOAT, range, literal, new RockNumber(value));
    }
}
