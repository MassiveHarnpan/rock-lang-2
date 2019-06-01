package rocklang.runtime;

import rocklang.exception.RockException;

public class RockBoolean extends Rock {

    public static RockBoolean TRUE = new RockBoolean(true);
    public static RockBoolean FALSE = new RockBoolean(false);


    public static RockBoolean valueOf(boolean bool) {
        return bool ? TRUE : FALSE;
    }


    private boolean bool;

    public RockBoolean(boolean bool) {
        super(RockType.BOOLEAN);
        this.bool = bool;
    }


    @Override
    public RockNumber asNumber() throws RockException {
        return new RockNumber(bool ? 1 : 0);
    }

    @Override
    public RockString asString() throws RockException {
        return new RockString(String.valueOf(bool));
    }

    @Override
    public RockBoolean asBoolean() throws RockException {
        return this;
    }


    @Override
    public Number getNumber() throws RockException {
        return bool ? 1 : 0;
    }

    @Override
    public String getString() throws RockException {
        return String.valueOf(bool);
    }

    @Override
    public boolean getBoolean() throws RockException {
        return bool;
    }

    @Override
    public Object getJavaObject() throws RockException {
        return bool;
    }

    @Override
    public String toString() {
        return String.valueOf(bool);
    }
}
