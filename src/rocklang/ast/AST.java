package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.token.Token;
import rocklang.util.FormatStream;

import java.io.IOException;

public interface AST {




    boolean isLeaf();

    AST[] children();

    AST child(int index);

    int childCount();

    Token token();



    Rock value(Environment env, Rock base) throws RockException;
    Rock assign(Environment env, Rock base, Rock value) throws RockException;

    AST simplify();

    void format(FormatStream fs) throws IOException;
}
