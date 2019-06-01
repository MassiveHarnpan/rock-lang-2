package rocklang.runtime;

import rocklang.exception.RockException;

public class RockNumber extends Rock {

    public static final RockNumber NaN = new RockNumber(Double.NaN);


    private Number number;

    public RockNumber(Number number) {
        super(RockType.NUMBER);
        this.number = number;
    }

    @Override
    public Number getNumber() throws RockException {
        return number;
    }

    @Override
    public String getString() throws RockException {
        return number.toString();
    }

    @Override
    public boolean getBoolean() throws RockException {
        return number.doubleValue() != 0;
    }

    @Override
    public RockNumber asNumber() throws RockException {
        return this;
    }

    @Override
    public RockString asString() throws RockException {
        return new RockString(String.valueOf(number));
    }

    @Override
    public RockBoolean asBoolean() throws RockException {
        return number.doubleValue() != 0 ? RockBoolean.TRUE : RockBoolean.FALSE;
    }



    @Override
    public Object getJavaObject() throws RockException {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
