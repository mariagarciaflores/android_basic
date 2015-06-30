package com.possiblelabs.calculator;

/**
 * Created by Griss Garcia on 28/06/2015.
 */
public class ConstantExpression implements Expression {

    private final float value;

    public ConstantExpression(float value) {
        this.value = value;
    }

    @Override
    public float evaluate() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Float.floatToIntBits(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConstantExpression other = (ConstantExpression) obj;
        if (Float.floatToIntBits(this.value) != Float.floatToIntBits(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
