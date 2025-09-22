package com.infosupport.oo.inneranonymous;

public class IsStringCondition implements Condition {
    @Override
    public boolean evaluate(Object input) {
        return input instanceof String;
    }
}
