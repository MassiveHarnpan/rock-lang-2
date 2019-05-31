package rocklang.tokenizer;

import rocklang.token.Token;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentStream;

public abstract class Tokenizer {


    public Token tokenize(DocumentStream ds) {
        ds.skipSpace();
        DocumentIndex checkPoint = ds.checkPoint();
        if (!ds.hasMore()) {
            return null;
        }
        Token token = doTokenize(ds, checkPoint);
        if (token == null) {
            ds.rollBack(checkPoint);
        }
        return token;
    }

    protected abstract Token doTokenize(DocumentStream ds, DocumentIndex startIndex);

}
