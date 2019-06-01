package rocklang.runtime;

import rocklang.exception.RockException;

import static rocklang.runtime.RockBoolean.TRUE;
import static rocklang.runtime.RockBoolean.FALSE;

public class RockString extends Rock {

    private String string;
    private Number number;

    public RockString(String string) {
        super(RockType.STRING);
        this.string = string;
        this.number = toNumber(string);
    }

    private static Number toNumber(String string) {
        if (string.matches("\\d*\\.\\d+")) {
            return Double.valueOf(string);
        } else if (string.matches("\\d+")) {
            return Integer.valueOf(string);
        } else {
            return Double.NaN;
        }
    }



    @Override
    public String getString() throws RockException {
        return string;
    }

    @Override
    public Number getNumber() throws RockException {
        return number;
    }

    @Override
    public boolean getBoolean() throws RockException {
        return "".equals(string);
    }


    @Override
    public RockNumber asNumber() throws RockException {
        return new RockNumber(number);
    }

    @Override
    public RockString asString() throws RockException {
        return this;
    }

    @Override
    public RockBoolean asBoolean() throws RockException {
        return "".equals(string) ? TRUE : FALSE;
    }

    @Override
    public Object getJavaObject() throws RockException {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }
}
