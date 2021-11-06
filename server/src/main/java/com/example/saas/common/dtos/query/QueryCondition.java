package com.example.saas.common.dtos.query;

import java.io.Serializable;

public class QueryCondition implements Serializable {
    private String field;
    private String compare;
    private Object value;
    private String andOr;

    public QueryCondition(String field, String compare, Object value, String andOr) {
        this.field = field;
        this.compare = compare;
        this.value = value;
        this.andOr = andOr;
    }

    public QueryCondition() {
    }

    public String getAndOr() {
        return andOr;
    }

    public String getCompare() {
        return compare;
    }

    public String getField() {
        return field;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param andOr the andOr to set
     */
    public void setAndOr(String andOr) {
        this.andOr = andOr;
    }

    /**
     * @param compare the compare to set
     */
    public void setCompare(String compare) {
        this.compare = compare;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public String toWhereSql() {
        String compare = this.compare;
        String value = this.value.toString();
        String andOr = this.andOr;
        if (this.compare.equals(":") || this.compare.equals("like")) {
            compare = "like";
            value = "%" + this.value + "%";
        }
        if (this.value instanceof String && !this.compare.equals("in")) {
            value = "\'" + value + "\'";
        } else {
            value = this.value.toString();
        }

        return field + " " + compare + " " + value + " " + andOr + " ";
    }
}