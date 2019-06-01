package rocklang.util;

import rocklang.ast.AST;

import java.util.List;

public class Utils {

    public static void cutList(List list, int size) {
        while (list.size() > size) {
            list.remove(list.size() - 1);
        }
    }
    public static AST[] simplifyASTList(AST[] list) {
        AST[] newChildren = new AST[list.length];
        for (int i = 0; i < list.length; i++) {
            newChildren[i] = list[i].simplify();
        }
        return newChildren;
    }

}
