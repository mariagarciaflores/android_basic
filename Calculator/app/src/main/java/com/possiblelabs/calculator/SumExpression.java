package com.possiblelabs.calculator;

/**
 * Created by Griss Garcia on 28/06/2015.
 */
public class SumExpression extends OperationExpression {

    public SumExpression(Expression left, Expression right) {
        super(left, right, "+");
    }
}
