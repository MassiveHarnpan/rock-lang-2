package rocklang.util;

import rocklang.ast.AST;
import rocklang.ast.Native;
import rocklang.exception.RockException;
import rocklang.runtime.Environment;
import rocklang.runtime.Rock;
import rocklang.runtime.RockNil;
import rocklang.runtime.RockNumber;

public class Functions {

    public static final AST PRINT = new Native() {
        @Override
        public Rock value(Environment env, Rock base) throws RockException {
            System.out.println(env.get("msg"));
            return RockNil.NIL;
        }
    };


    public static final AST TIME = new Native() {
        @Override
        public Rock value(Environment env, Rock base) throws RockException {
            return new RockNumber(System.currentTimeMillis());
        }
    };

    public static final AST PRINT_ENV = new Native() {
        @Override
        public Rock value(Environment env, Rock base) throws RockException {
            System.out.println(env);
            return RockNil.NIL;
        }
    };

}
