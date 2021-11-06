package com.example.saas.common.dtos;

import com.example.saas.common.dtos.query.PageParam;
import com.example.saas.common.dtos.query.QueryCondition;
import com.example.saas.common.dtos.query.QueryParam;

public class Q {
    private QueryParam queryParam = new QueryParam();

    public Q $like(String field, Object value) {
        this.queryParam.getQueryConditions().add(new QueryCondition(field, "like", value, "and"));
        return this;
    }

    public Q $in(String field, String value) {
        this.queryParam.getQueryConditions().add(new QueryCondition(field, "in", value, "and"));
        return this;
    }

    public Q $eq(String field, Integer value) {
        this.queryParam.getQueryConditions().add(new QueryCondition(field, "=", value, "and"));
        return this;
    }

    public Q $eq(String field, String value) {
        this.queryParam.getQueryConditions().add(new QueryCondition(field, "=", value, "and"));
        return this;
    }

    public Q or(String field, Object value) {
        this.queryParam.getQueryConditions().add(new QueryCondition(field, "like", value, "and"));
        return this;
    }

    public static Q page(Integer pageIndex, Integer pageSize) {
        Q query = new Q();
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(pageIndex);
        pageParam.setPageSize(pageSize);
        pageParam.setPageable(true);
        query.queryParam.setPageParam(pageParam);
        return query;
    }

    public static Q all() {
        Q query = new Q();
        PageParam pageParam = new PageParam();
        pageParam.setPageable(false);
        query.queryParam.setPageParam(pageParam);
        return query;
    }

    public static Q one() {
        Q query = new Q();
        PageParam pageParam = new PageParam();
        pageParam.setPageIndex(0);
        pageParam.setPageSize(1);
        pageParam.setPageable(true);
        query.queryParam.setPageParam(pageParam);
        return query;
    }

    public QueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(QueryParam queryParam) {
        this.queryParam = queryParam;
    }
}