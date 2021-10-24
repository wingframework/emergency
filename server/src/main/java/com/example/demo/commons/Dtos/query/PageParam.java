package com.example.demo.commons.Dtos.query;

public class PageParam {
    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    private String sortField = "";// "排序的字段",
    private Boolean sortByAsc = true;// "升序还是降序", asc /desc
    /** 是否进行分页 */
    private Boolean pageable = true;

    /**
     * @return the pageable
     */
    public Boolean getPageable() {
        return pageable;
    }

    /**
     * @return the pageIndex
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @return the sortByAsc
     */
    public Boolean getSortByAsc() {
        return sortByAsc;
    }

    /**
     * @param pageable the pageable to set
     */
    public void setPageable(Boolean pageable) {
        this.pageable = pageable;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param sortByAsc the sortByAsc to set
     */
    public void setSortByAsc(Boolean sortByAsc) {
        this.sortByAsc = sortByAsc;
    }

    /**
     * @param sortField the sortField to set
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    /**
     * @return the sortField
     */
    public String getSortField() {
        return sortField;
    }
}
