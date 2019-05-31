package rocklang.ast;

import rocklang.runtime.Enviroument;
import rocklang.token.Token;
import rocklang.util.FormatStream;

import java.io.IOException;

public interface AST {




    boolean isLeaf();

    AST[] children();

    AST child(int index);

    int childCount();

    Token token();



    Object value(Enviroument env, Object base);
    Object assign(Enviroument env, Object base);

    AST simplify();

    void format(FormatStream fs) throws IOException;
}
