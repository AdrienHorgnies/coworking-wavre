package com.ifosup.coworking.service;

public class SpaceCriterion {
    public String key;
    public ConditionOperator operator;
    public String value;

    public SpaceCriterion(String key, String operator, String value) {
        this.key = key;
        this.operator = ConditionOperator.valueOf(operator.toUpperCase());
        this.value = value;
    }

    @Override
    public String toString() {
        return "SpaceCriterion{" +
            "key='" + key + '\'' +
            ", operator=" + operator +
            ", value='" + value + '\'' +
            '}';
    }
}
