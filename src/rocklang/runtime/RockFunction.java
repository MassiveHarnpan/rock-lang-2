package rocklang.runtime;

import rocklang.ast.AST;
import rocklang.exception.RockException;

public class RockFunction extends Rock {

    private Environment environment;
    private String[] parameters;
    private AST functional;

    public RockFunction(Environment environment, String[] parameters, AST functional) {
        super(RockType.FUNCTION);
        this.environment = environment;
        this.parameters = parameters;
        this.functional = functional;
    }

    @Override
    public RockBoolean asBoolean() throws RockException {
        return RockBoolean.TRUE;
    }

    @Override
    public Rock invoke(Rock[] arguments) throws RockException {
        if (parameters.length != arguments.length) {
            throw new RockException("Argument number not match: except " + arguments.length + " of " + parameters.length);
        }
        Environment env = new Environment(environment);
        for (int i = 0; i < parameters.length; i++) {
            env.set(parameters[i], arguments[i]);
        }
        env.set("this", this);
        return functional.value(env, null);
    }
}