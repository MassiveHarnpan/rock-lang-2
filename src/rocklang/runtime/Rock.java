package rocklang.runtime;

import rocklang.exception.RockException;

public class Rock {

    private RockType type;

    public Rock(RockType type) {
        this.type = type;
    }

    public RockType type() {
        return type;
    }

    public Number getNumber() throws RockException {
        throw new RockException("Cannot convert to number");
    }

    public String getString() throws RockException {
        throw new RockException("Cannot convert to string");
    }

    public boolean getBoolean() throws RockException {
        throw new RockException("Cannot convert to boolean");
    }



    public RockNumber asNumber() throws RockException {
        throw new RockException("Cannot convert to number");
    }

    public RockString asString() throws RockException {
        throw new RockException("Cannot convert to string");
    }

    public RockBoolean asBoolean() throws RockException {
        throw new RockException("Cannot convert to boolean");
    }


    public Rock invoke(Rock[] arguments) throws RockException {
        throw new RockException("Cannot invoke");
    }






    public Rock getMember(Rock key) throws RockException {
        throw new RockException("Cannot get member");
    }

    public Rock setMember(Rock key, Rock value) throws RockException {
        throw new RockException("Cannot set member");
    }

    public Object getJavaObject() throws RockException {
        throw new RockException("Cannot get Java Object");
    }
}
