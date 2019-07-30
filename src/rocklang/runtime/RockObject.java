package rocklang.runtime;

import rocklang.exception.RockException;

public class RockObject extends Rock {

    private RockClass rockClass;
    private Environment thisEnv;

    public RockObject(RockClass rockClass) {
        super(RockType.OBJECT);
        this.rockClass = rockClass;
    }


    @Override
    public Rock getMember(Rock key) throws RockException {
        if (key.type() != RockType.STRING) {
            return super.getMember(key);
        }
        return thisEnv.getLocal(key.getString());
    }


    @Override
    public Rock setMember(Rock key, Rock value) throws RockException {
        if (key.type() != RockType.STRING) {
            return super.getMember(key);
        }
        return thisEnv.setLocal(key.getString(), value);
    }


    @Override
    public String toString() {
        return "<object instanceof " + rockClass.name() + ">";
    }
}
