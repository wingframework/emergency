package com.example.demo.commons.entitys.queryTemplate;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "metaObject", uniqueConstraints = { @UniqueConstraint(columnNames = "objectCode") })
public class MetaObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metaId;
    @NotBlank(message = "元数据编码不能为空")
    @Column(nullable = false, length = 20)
    private String objectCode;
    @Column(nullable = true, length = 50)
    private String objectName;
    @NotBlank(message = "更新表名不能为空")
    @Column(nullable = false, length = 50)
    private String tableName;
    @Column(nullable = true)
    private Boolean isSingle;
    @Column(nullable = true)
    private Boolean isCelledit;
    @Column(nullable = true)
    private Boolean isShowNum;
    @Column(nullable = true)
    private Boolean isFirstLoad;
    @Column(nullable = true, length = 500)
    private String filter;
    @NotBlank(message = "查询sql不能为空")
    @Column(nullable = false, length = 4000)
    private String querySql;
    @Column(nullable = true, length = 20)
    private String defaultOrder;
    private String bizIntercept;
    @Column(nullable = true, length = 800)
    private String config;
    @NotBlank(message = "查询分类名不能为空")
    @Column(nullable = false)
    private String groupName;
    @NotBlank(message = "主键属性名不能为空")
    @Column(nullable = false, length = 50)
    private String pkKey;
    @Column(nullable = true, length = 50)
    private String parentKey;

    // @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "metaObject", orphanRemoval = true)
    @Cascade(value = { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE })
    public List<MetaField> metaFields;

    public MetaObject() {
    }

    public Integer getMetaId() {
        return metaId;
    }

    public void setMetaId(Integer metaId) {
        this.metaId = metaId;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Boolean single) {
        isSingle = single;
    }

    public Boolean getIsCelledit() {
        return isCelledit;
    }

    public void setIsCelledit(Boolean celledit) {
        isCelledit = celledit;
    }

    public Boolean getIsShowNum() {
        return isShowNum;
    }

    public void setIsShowNum(Boolean showNum) {
        isShowNum = showNum;
    }

    public Boolean getIsFirstLoad() {
        return isFirstLoad;
    }

    public void setIsFirstLoad(Boolean firstLoad) {
        isFirstLoad = firstLoad;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public String getDefaultOrder() {
        return defaultOrder;
    }

    public void setDefaultOrder(String defaultOrder) {
        this.defaultOrder = defaultOrder;
    }

    public String getBizIntercept() {
        return bizIntercept;
    }

    public void setBizIntercept(String bizIntercept) {
        this.bizIntercept = bizIntercept;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPkKey() {
        return pkKey;
    }

    public void setPkKey(String pkKey) {
        this.pkKey = pkKey;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public List<MetaField> getMetaFields() {
        return metaFields;
    }

    public void setMetaFields(List<MetaField> metaFields) {
        this.metaFields = metaFields;
    }

    public void addMetaField(MetaField metaField) {
        if (!metaFields.contains(metaField)) {
            metaField.setMetaObject(this);
            this.metaFields.add(metaField);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MetaObject that = (MetaObject) o;
        return Objects.equals(metaId, that.metaId) && Objects.equals(objectCode, that.objectCode)
                && Objects.equals(objectName, that.objectName) && Objects.equals(tableName, that.tableName)
                && Objects.equals(isSingle, that.isSingle) && Objects.equals(isCelledit, that.isCelledit)
                && Objects.equals(isShowNum, that.isShowNum) && Objects.equals(isFirstLoad, that.isFirstLoad)
                && Objects.equals(filter, that.filter) && Objects.equals(querySql, that.querySql)
                && Objects.equals(defaultOrder, that.defaultOrder) && Objects.equals(bizIntercept, that.bizIntercept)
                && Objects.equals(config, that.config) && Objects.equals(groupName, that.groupName)
                && Objects.equals(pkKey, that.pkKey) && Objects.equals(parentKey, that.parentKey)
                && Objects.equals(metaFields, that.metaFields);
    }

    @Override
    public int hashCode() {

        return Objects.hash(metaId, objectCode, objectName, tableName, isSingle, isCelledit, isShowNum, isFirstLoad,
                filter, querySql, defaultOrder, bizIntercept, config, groupName, pkKey, parentKey, metaFields);
    }
}
