package com.example.demo.commons.Dtos.query;

public class SummaryParam {
    private String fieldName;
    private String sumName;
    private String sumTitle;
    private String sumType;
    private String sumValue;

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @return the sumName
     */
    public String getSumName() {
        return sumName;
    }

    /**
     * @return the sumTitle
     */
    public String getSumTitle() {
        return sumTitle;
    }

    /**
     * @return the sumType
     */
    public String getSumType() {
        return sumType;
    }

    /**
     * @return the sumValue
     */
    public String getSumValue() {
        return sumValue;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @param sumName the sumName to set
     */
    public void setSumName(String sumName) {
        this.sumName = sumName;
    }

    /**
     * @param sumTitle the sumTitle to set
     */
    public void setSumTitle(String sumTitle) {
        this.sumTitle = sumTitle;
    }

    /**
     * @param sumType the sumType to set
     */
    public void setSumType(String sumType) {
        this.sumType = sumType;
    }

    /**
     * @param sumValue the sumValue to set
     */
    public void setSumValue(String sumValue) {
        this.sumValue = sumValue;
    }
}