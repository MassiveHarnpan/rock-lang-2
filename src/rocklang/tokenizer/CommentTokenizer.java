package rocklang.tokenizer;

import rocklang.token.Token;
import rocklang.token.TokenType;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentRange;
import rocklang.util.DocumentStream;

public class CommentTokenizer extends Tokenizer {
    @Override
    protected Token doTokenize(DocumentStream ds, DocumentIndex startIndex) {
        if (ds.read() != '#') {
            return null;
        }
        ds.skipToNextLine();
        DocumentRange range = ds.rangeFrom(startIndex);
        String literal = ds.getDocument().range(range);
        return new Token(TokenType.COMMENT, range, literal, literal.substring(1));
    }
}
