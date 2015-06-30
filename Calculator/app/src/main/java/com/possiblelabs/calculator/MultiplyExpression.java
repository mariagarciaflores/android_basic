package com.possiblelabs.calculator;

/**
 * Created by Griss Garcia on 28/06/2015.
 */
public class MultiplyExpression extends OperationExpression {

    public MultiplyExpression(Expression left, Expression right) {
        super(left, right, "*");
    }

}
