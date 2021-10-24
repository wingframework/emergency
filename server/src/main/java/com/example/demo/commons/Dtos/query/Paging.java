package com.example.demo.commons.Dtos.query;

import java.io.Serializable;
import java.util.List;

public class Paging<T> implements Serializable {
    private Integer count;
    private List<SummaryParam> summaryParam;
    private T[] rows;

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @return the rows
     */
    public T[] getRows() {
        return rows;
    }

    /**
     * @return the summaryParam
     */
    public List<SummaryParam> getSummaryParam() {
        return summaryParam;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(T[] rows) {
        this.rows = rows;
    }

    /**
     * @param summaryParam the summaryParam to set
     */
    public void setSummaryParam(List<SummaryParam> summaryParam) {
        this.summaryParam = summaryParam;
    }

}