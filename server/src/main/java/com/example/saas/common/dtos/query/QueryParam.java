package com.example.saas.common.dtos.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryParam implements Serializable {
    private List<QueryCondition> queryConditions = new ArrayList<QueryCondition>();
    private List<SummaryParam> summaryParam;
    private List<OrderBy> orderList;
    private PageParam pageParam = new PageParam();
    /*
     * 查询参数HashMap，可以有多个参数 处理如select * from table where
     * field1=@fieldValue的情况,参数名为@fieldValue
     * 前端若想替换参数@fieldValue的值为1，则可以在queryAttributes中put("@fieldValue","1")
     */
    private List<QueryAttribute> queryAttributes;

    /**
     * @return the queryAttributes
     */
    public List<QueryAttribute> getQueryAttributes() {
        return queryAttributes;
    }

    /**
     * @param queryAttributes the queryAttributes to set
     */
    public void setQueryAttributes(List<QueryAttribute> queryAttributes) {
        this.queryAttributes = queryAttributes;
    }

    /**
     * @return the orderList
     */
    public List<OrderBy> getOrderList() {
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(List<OrderBy> orderList) {
        this.orderList = orderList;
    }

    /**
     * @return the pageParam
     */
    public PageParam getPageParam() {
        return pageParam;
    }

    /**
     * @return the queryConditions
     */
    public List<QueryCondition> getQueryConditions() {
        return queryConditions;
    }

    /**
     * @return the summaryParam
     */
    public List<SummaryParam> getSummaryParam() {
        return summaryParam;
    }

    /**
     * @param pageParam the pageParam to set
     */
    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    /**
     * @param queryConditions the queryConditions to set
     */
    public void setQueryConditions(List<QueryCondition> queryConditions) {
        this.queryConditions = queryConditions;
    }

    /**
     * @param summaryParam the summaryParam to set
     */
    public void setSummaryParam(List<SummaryParam> summaryParam) {
        this.summaryParam = summaryParam;
    }
}