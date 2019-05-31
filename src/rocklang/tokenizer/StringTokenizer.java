package rocklang.tokenizer;

import rocklang.token.Token;
import rocklang.token.TokenType;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentRange;
import rocklang.util.DocumentStream;

public class StringTokenizer extends Tokenizer {
    @Override
    protected Token doTokenize(DocumentStream ds, DocumentIndex startIndex) {
        char quote = '"';
        if (ds.next() == '"' || ds.next() == '\'') {
            quote = ds.read();
        } else {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        boolean quoted = false;
        while (ds.hasMore()) {
            char ch = ds.read();
            if (ch == '\\' && quote == '"') {
                if (ds.hasMore()) {
                    ch = readEscape(ds);
                } else {
                    return null;
                }
            } else if (ch == quote) {
                quoted = true;
                break;
            }
            builder.append(ch);
        }
        if (!quoted) {
            return null;
        }

        DocumentRange range = ds.rangeFrom(startIndex);
        return new Token(TokenType.STRING, range, ds.getDocument().range(range), builder.toString());
    }

    private char readEscape(DocumentStream ds) {
        char escape = ds.read();
        switch (escape) {
            case 'n': escape = '\n'; break;
            case 't': escape = '\t'; break;
            case 'r': escape = '\r'; break;
            case '\\': escape = '\\'; break;
        }
        return escape;
    }
}
