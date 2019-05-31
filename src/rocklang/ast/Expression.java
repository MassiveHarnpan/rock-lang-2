package rocklang.ast;

import rocklang.util.FormatStream;

import java.io.IOException;

public class Expression extends ASTList {
    public Expression(AST[] children) {
        super(children);
    }

    @Override
    public AST simplify() {
        if (childCount() == 1) {
            return child(0).simplify();
        }
        AST[] newChildren = new AST[childCount()];
        for (int i = 0; i < childCount(); i++) {
            newChildren[i] = child(i).simplify();
        }
        return new Expression(newChildren);
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        for (int i = 0; i < childCount(); i++) {
            child(i).format(fs);
            if (i != childCount() - 1) {
                fs.print(" ");
            }
        }
    }
}
