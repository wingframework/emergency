package com.example.demo.commons.entitys.queryTemplate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "metaField")
public class MetaField implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 20)
    private String objectCode;
    @Column(nullable = true)
    private Boolean isQuery;
    @Column(nullable = true)
    private Boolean isShow;
    @Column(nullable = true)
    private Boolean isUpdate;
    @Column(nullable = true, length = 200)
    private String placeholder;
    @Column(nullable = true, length = 800)
    private String config;
    @NotBlank(message = "字段类型不能为空")
    @Column(nullable = false, length = 10)
    private String fieldType;
    @NotBlank(message = "字段名不能为空")
    @Column(nullable = false, length = 20)
    private String fieldName;
    private Integer recno;
    private String alias;
    @Column(nullable = true, length = 200)
    private String presetValue;
    private Integer displayWidth;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "metaId", updatable = false)
    private MetaObject metaObject;

    public MetaField() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public Boolean getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(Boolean query) {
        isQuery = query;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean update) {
        isUpdate = update;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getRecno() {
        return recno;
    }

    public void setRecno(Integer recno) {
        this.recno = recno;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPresetValue() {
        return presetValue;
    }

    public void setPresetValue(String presetValue) {
        this.presetValue = presetValue;
    }

    public Integer getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(Integer displayWidth) {
        this.displayWidth = displayWidth;
    }

    public MetaObject getMetaObject() {
        return metaObject;
    }

    public void setMetaObject(MetaObject metaObject) {
        this.metaObject = metaObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MetaField metaField = (MetaField) o;
        return Objects.equals(id, metaField.id) && Objects.equals(objectCode, metaField.objectCode)
                && Objects.equals(isQuery, metaField.isQuery) && Objects.equals(isShow, metaField.isShow)
                && Objects.equals(isUpdate, metaField.isUpdate) && Objects.equals(placeholder, metaField.placeholder)
                && Objects.equals(config, metaField.config) && Objects.equals(fieldType, metaField.fieldType)
                && Objects.equals(fieldName, metaField.fieldName) && Objects.equals(recno, metaField.recno)
                && Objects.equals(alias, metaField.alias) && Objects.equals(presetValue, metaField.presetValue)
                && Objects.equals(displayWidth, metaField.displayWidth)
                && Objects.equals(metaObject, metaField.metaObject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, objectCode, isQuery, isShow, isUpdate, placeholder, config, fieldType, fieldName, recno,
                alias, presetValue, displayWidth, metaObject);
    }
}
