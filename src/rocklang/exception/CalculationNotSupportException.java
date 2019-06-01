package rocklang.exception;

import rocklang.ast.AST;

public class CalculationNotSupportException extends RockException {

    private AST former;
    private String operator;
    private AST latter;

    public CalculationNotSupportException(AST former, String operator, AST latter) {
        super("Cannot calculate: " + former + " " + operator + " " + latter);
        this.former = former;
        this.operator = operator;
        this.latter = latter;
    }
}
