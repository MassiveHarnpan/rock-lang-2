package rocklang.ast;

import rocklang.util.FormatStream;

import java.io.IOException;

public class BlockStmt extends ASTList {
    public BlockStmt(AST[] children) {
        super(children);
    }


    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("{").levelUp();
        for (int i = 0; i < childCount(); i++) {
            fs.newLine();
            child(i).format(fs);
            fs.print(";");
        }
        fs.levelDown().newLine().print("}");
    }
}
