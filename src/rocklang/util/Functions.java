package rocklang.util;

import rocklang.ast.AST;
import rocklang.ast.Native;
import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;

public class Functions {

    public static final AST PRINT = new Native() {
        @Override
        public Rock value(Environment env, Rock base) throws RockException {
            System.out.println(env.get("msg"));
            return RockNil.NIL;
        }
    };


}
