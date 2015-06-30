package com.possiblelabs.calculator;

/**
 * Created by Griss Garcia on 28/06/2015.
 */
public abstract class OperationExpression implements Expression {

    private final Expression left;
    private final Expression right;
    protected final String operator;

    public OperationExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public float evaluate() {
        switch (operator) {
            case "+":
                return left.evaluate() + right.evaluate();
            case "-":
                return left.evaluate() - right.evaluate();
            case "*":
                return left.evaluate() * right.evaluate();
            case "/":
                return left.evaluate() / right.evaluate();
            default :
                throw new IllegalArgumentException("unsupported math operation");
        }
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", left.toString(), operator, right.toString());
    }

}
