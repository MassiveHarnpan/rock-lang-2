package rocklang.util;

import rocklang.exception.RockException;
import rocklang.runtime.Rock;
import rocklang.runtime.RockBoolean;
import rocklang.runtime.RockNumber;
import rocklang.runtime.RockString;

public class Calculation {


    public static Rock calculate(Rock former, String operator, Rock latter) throws RockException {
        if (former == null) {
            switch (operator) {
                case "!": return RockBoolean.valueOf(!latter.getBoolean());
                case "+": return latter.asNumber();
                case "-": return new RockNumber(-latter.getNumber().doubleValue());
                default: throw new RockException("unsupported operator: " + operator);
            }
        }

        if ("..".equals(operator)) {
            return new RockString(former.getString() + latter.getString());
        }

        Number v1 = former.getNumber().doubleValue();
        Number v2 = latter.getNumber().doubleValue();

        if (v1 instanceof Integer && v2 instanceof Integer) {
            Rock result = calcIntegers(v1.intValue(), operator, v2.intValue());
            return result;
        } else {
            Rock result = calcDecimals(v1.doubleValue(), operator, v2.doubleValue());
            return result;
        }
    }

    public static Rock calcIntegers(int v1, String operator, int v2) throws RockException {
        switch (operator) {
            case "+": return new RockNumber(v1 + v2);
            case "-": return new RockNumber(v1 - v2);
            case "*": return new RockNumber(v1 * v2);
            case "/": return new RockNumber(v1 / v2);
            case "%": return new RockNumber(v1 % v2);
            case "^": return new RockNumber(Math.pow(v1, v2));
            case ">": return RockBoolean.valueOf(v1 > v2);
            case "<": return RockBoolean.valueOf(v1 < v2);
            case ">=": return RockBoolean.valueOf(v1 >= v2);
            case "<=": return RockBoolean.valueOf(v1 <= v2);
            case "==": return RockBoolean.valueOf(v1 == v2);
            case "!=": return RockBoolean.valueOf(v1 != v2);
            default: throw new RockException("unsupported operator: " + operator);
        }
    }

    public static Rock calcDecimals(double v1, String operator, double v2) throws RockException {
        switch (operator) {
            case "+": return new RockNumber(v1 + v2);
            case "-": return new RockNumber(v1 - v2);
            case "*": return new RockNumber(v1 * v2);
            case "/": return new RockNumber(v1 / v2);
            case "%": return new RockNumber(v1 % v2);
            case "^": return new RockNumber(Math.pow(v1, v2));
            case ">": return RockBoolean.valueOf(v1 > v2);
            case "<": return RockBoolean.valueOf(v1 < v2);
            case ">=": return RockBoolean.valueOf(v1 >= v2);
            case "<=": return RockBoolean.valueOf(v1 <= v2);
            case "==": return RockBoolean.valueOf(v1 == v2);
            case "!=": return RockBoolean.valueOf(v1 != v2);
            default: throw new RockException("unsupported operator: " + operator);
        }
    }
}
