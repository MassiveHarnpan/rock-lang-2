package rocklang.util;

import rocklang.exception.RockException;
import rocklang.runtime.Rock;
import rocklang.runtime.RockBoolean;
import rocklang.runtime.RockNumber;

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

        double v1 = former.getNumber().doubleValue();
        double v2 = latter.getNumber().doubleValue();

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
