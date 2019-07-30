package rocklang.runtime;

import rocklang.exception.RockException;

public class RockClass extends Rock {

    private String name;
//    private RockClass superClass;
    private Environment classEnv;
    private Environment environment;

    public RockClass() {
        super(RockType.CLASS);
    }

    @Override
    public RockString asString() throws RockException {
        return new RockString(toString());
    }

    @Override
    public String getString() throws RockException {
        return toString();
    }

    @Override
    public RockBoolean asBoolean() throws RockException {
        return RockBoolean.TRUE;
    }

    @Override
    public boolean getBoolean() throws RockException {
        return true;
    }

    @Override
    public Rock invoke(Rock[] arguments) throws RockException {
        RockObject obj = new RockObject();
        Environment env = new Environment(environment);
        env.setLocal("this", obj);
        return super.invoke(arguments);
    }

    @Override
    public Rock getMember(Rock key) throws RockException {
        if (key.type() != RockType.STRING) {
            return super.getMember(key);
        }
        String name = key.getString();
        return classEnv.get(name);
    }

    @Override
    public Rock setMember(Rock key, Rock value) throws RockException {
        if (key.type() != RockType.STRING) {
            return super.setMember(key, value);
        }
        String name = key.getString();
        return classEnv.getLocal(name);
    }

    @Override
    public String toString() {
        return "<class" + name + ">";
    }

    public String name() {
        return name;
    }
}
