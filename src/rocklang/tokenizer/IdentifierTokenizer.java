package rocklang.tokenizer;

import rocklang.parser.Parser;
import rocklang.token.Token;
import rocklang.token.TokenType;
import rocklang.util.DocumentIndex;
import rocklang.util.DocumentRange;
import rocklang.util.DocumentStream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IdentifierTokenizer extends Tokenizer {

    static String[] identifiers = new String[] {
            "++", "--",
            "=>", "==", "!=", ">=", "<=",
            "&&", "||",
            "..", "+", "-", "*", "/", "%", "^",
            "(", ")", "[", "]", "{", "}",
            ",", ";", "?", ":", "#", "@", ".",
            "!", "<", ">",
            "=",
            "if", "else", "while", "for",
            "class", "def", "fn", "let",
    };


    @Override
    protected Token doTokenize(DocumentStream ds, DocumentIndex startIndex) {
        String identifier = null;
        for (int i = 0; i < identifiers.length; i++) {
            String target = identifiers[i];
            if (isNext(ds, target)) {
                identifier = target;
                break;
            }
            ds.rollBack(startIndex);
        }
        if (identifier == null) {
            return null;
        }
        DocumentRange range = ds.rangeFrom(startIndex);
        return new Token(TokenType.IDENTIFIER, range, identifier, null);
    }

    private boolean isNext(DocumentStream ds, String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!ds.hasMore() || ds.read() != value.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
