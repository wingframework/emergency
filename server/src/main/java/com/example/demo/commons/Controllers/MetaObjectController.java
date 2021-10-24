package com.example.demo.commons.Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.commons.Dtos.GetFieldsDto;
import com.example.demo.commons.entitys.queryTemplate.MetaField;
import com.example.demo.commons.entitys.queryTemplate.MetaObject;
import com.example.demo.commons.sevices.SqlEntityService;
import com.example.demo.commons.sevices.jpa.MetaFieldJPA;
import com.example.demo.commons.sevices.jpa.MetaObjectJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(tags = "元数据操作", description = "元数据增删改查")
public class MetaObjectController {
    @Autowired
    private SqlEntityService sqlEntityService;
    @Autowired
    private MetaObjectJPA metaObjectJPA;
    @Autowired
    MetaFieldJPA metaFieldJPA;

    @RequestMapping(value = "/field", method = RequestMethod.POST)
    public Rtn<List<JSONObject>> getFields(@RequestBody GetFieldsDto input) {
        List<JSONObject> fields = this.sqlEntityService.getFieldInfoBySql(input.getSql());
        return Rtn.Success(fields);
    }

    @RequestMapping(value = "/addMetaObject", method = RequestMethod.POST)
    public Rtn<Boolean> AddMetaObject(@RequestBody MetaObject meta) {
        List<MetaField> fields = meta.getMetaFields();
        meta.setMetaFields(new ArrayList<MetaField>());

        MetaObject insert = metaObjectJPA.saveAndFlush(meta);
        fields.stream().forEach(t -> {
            t.setMetaObject(insert);
            t.setObjectCode(insert.getObjectCode());
        });
        metaFieldJPA.saveAll(fields);
        return Rtn.Success(true);

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Rtn<List<MetaObject>> GetMetaObjectList() {
        return Rtn.Success(metaObjectJPA.findAll());
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Rtn<MetaObject> detail(String objectCode) {
        return Rtn.Success(metaObjectJPA.findByObjectCode(objectCode));
    }
}