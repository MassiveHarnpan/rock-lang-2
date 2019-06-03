package rocklang.parser;

import rocklang.ast.AST;
import rocklang.ast.ASTLeaf;
import rocklang.token.TokenStream;

import java.util.ArrayList;
import java.util.List;

public class PatternParser extends NonTerminateParser {


    private Parser prefixParser;
    private Parser suffixParser;
    private Parser elementParser;
    private Parser splitterParser;
    private boolean atLeastOneElement;
    private boolean recordSplitter;
    private boolean allowEmptyElement;

    public PatternParser(Parser prefixParser, Parser suffixParser, Parser elementParser, Parser splitterParser, boolean atLeastOneElement, boolean recordSplitter, boolean allowEmptyElement) {
        this.prefixParser = prefixParser;
        this.suffixParser = suffixParser;
        this.elementParser = elementParser;
        this.splitterParser = splitterParser;
        this.atLeastOneElement = atLeastOneElement;
        this.recordSplitter = recordSplitter;
        this.allowEmptyElement = allowEmptyElement;
    }

    @Override
    protected boolean doParse(TokenStream ts, List<AST> scope) {
        List<AST> res = new ArrayList<>();
        if (prefixParser != null && !prefixParser.parse(ts, res)) {
            System.out.println("return false");
            return false;
        }

        if (elementParser != null && elementParser.parse(ts, scope)) {
            while (true) {
                if (splitterParser != null && !splitterParser.parse(ts, recordSplitter ? scope : res)) {
                    break;
                }
                if (!elementParser.parse(ts, scope)) {
                    if (splitterParser != null && !allowEmptyElement) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
        } else if (atLeastOneElement) {
            return false;
        }

        if (suffixParser != null && !suffixParser.parse(ts, res)) {
            return false;
        }
        return true;
    }







    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Parser prefixParser = null;
        private Parser suffixParser = null;
        private Parser elementParser = null;
        private Parser splitterParser = null;
        private boolean atLeastOneElement = false;
        private boolean recordSplitter = false;
        private boolean allowEmptyElement = false;

        public Builder prefix(Parser prefixParser) {
            this.prefixParser = prefixParser;
            return this;
        }

        public Builder suffix(Parser suffixParser) {
            this.suffixParser = suffixParser;
            return this;
        }

        public Builder element(Parser elementParser) {
            this.elementParser = elementParser;
            return this;
        }

        public Builder splitter(Parser splitterParser) {
            this.splitterParser = splitterParser;
            return this;
        }

        public Builder prefix(String... allowedLiterals) {
            this.prefixParser = new TokenParser(allowedLiterals).asAST(ASTLeaf.class);
            return this;
        }

        public Builder suffix(String... allowedLiterals) {
            this.suffixParser = new TokenParser(allowedLiterals).asAST(ASTLeaf.class);
            return this;
        }

        public Builder element(String... allowedLiterals) {
            this.elementParser = new TokenParser(allowedLiterals).asAST(ASTLeaf.class);
            return this;
        }

        public Builder splitter(String... allowedLiterals) {
            this.splitterParser = new TokenParser(allowedLiterals).asAST(ASTLeaf.class);
            return this;
        }





        public Builder atLeastOneElement() {
            this.atLeastOneElement = true;
            return this;
        }

        public Builder allowEmptyElement() {
            this.allowEmptyElement = true;
            return this;
        }

        public Builder recordSplitter() {
            this.recordSplitter = true;
            return this;
        }






        public PatternParser build() {
            return new PatternParser(
                    this.prefixParser,
                    this.suffixParser,
                    this.elementParser,
                    this.splitterParser,
                    this.atLeastOneElement,
                    this.recordSplitter,
                    this.allowEmptyElement);
        }
    }


}
