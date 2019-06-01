package rocklang.runtime;

import rocklang.exception.RockException;

import static rocklang.runtime.RockBoolean.FALSE;

public class RockNil extends Rock {

    public static final RockNil NIL = new RockNil();


    public RockNil() {
        super(RockType.NIL);
    }


    @Override
    public Number getNumber() throws RockException {
        return 0;
    }

    @Override
    public String getString() throws RockException {
        return "nil";
    }

    @Override
    public boolean getBoolean() throws RockException {
        return false;
    }

    @Override
    public RockString asString() throws RockException {
        return new RockString("nil");
    }

    @Override
    public RockNumber asNumber() throws RockException {
        return new RockNumber(0);
    }

    @Override
    public RockBoolean asBoolean() throws RockException {
        return FALSE;
    }


    @Override
    public Object getJavaObject() throws RockException {
        return null;
    }

    @Override
    public String toString() {
        return "nil";
    }
}
