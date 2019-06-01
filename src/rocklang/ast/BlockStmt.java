package rocklang.ast;

import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.util.FormatStream;

import java.io.IOException;

import static rocklang.util.Utils.simplifyASTList;

public class BlockStmt extends ASTList {
    public BlockStmt(AST[] children) {
        super(children);
    }


    @Override
    public Rock value(Environment env, Rock base) throws RockException {
        Rock result = RockNil.NIL;
        for (int i = 0; i < childCount(); i++) {
            result = child(i).value(env, null);
        }
        return result;
    }

    @Override
    public AST simplify() {
        return new BlockStmt(simplifyASTList(children()));
    }

    @Override
    public void format(FormatStream fs) throws IOException {
        fs.print("{").levelUp();
        for (int i = 0; i < childCount(); i++) {
            fs.newLine();
            child(i).format(fs);
        }
        fs.levelDown().newLine().print("}");
    }
}
