package com.possiblelabs.calculator;

/**
 * Created by Griss Garcia on 28/06/2015.
 */
public class SubstractExpression extends OperationExpression {

    public SubstractExpression(Expression left, Expression right) {
        super(left, right, "-");
    }
}
