package com.possiblelabs.calculator;

/**
 * Created by Griss Garcia on 28/06/2015.
 */
public class ExpressionParser {

    public Expression parse(String expression) {

        Expression result = parseConstant(expression);
        if (result != null) {
            return result;
        }
        String operator = searchOperator(expression);
        int number = expression.indexOf(operator);
        Expression left = parse(expression.substring(0, number));
        Expression right = parse(expression.substring(number + 1));
        return solveExpression(left, right, operator);

    }

    private Expression parseConstant(String expression) {
        try {
            float parseFloat = Float.parseFloat(expression);
            return new ConstantExpression(parseFloat);
        } catch (NumberFormatException error) {
            return null;
        }
    }

    private String searchOperator(String expression) {
        if (expression.contains("+")) {
            return "+";
        }
        else if (expression.contains("-")) {
            return "-";
        }
        else if (expression.contains("*")) {
            return "*";
        }
        else if (expression.contains("/")) {
            return "/";
        }
        throw new IllegalArgumentException();
    }

    private Expression solveExpression(Expression left, Expression right, String operator) {
        switch(operator) {
            case "+":
                return new SumExpression(left, right);
            case "-":
                return new SubstractExpression(left, right);
            case "*":
                return new MultiplyExpression(left, right);
            case "/":
                return new DivideExpression(left, right);
            default:
                throw new IllegalArgumentException("error");
        }
    }
}
