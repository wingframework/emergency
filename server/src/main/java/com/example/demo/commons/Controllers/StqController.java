package com.example.demo.commons.Controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.commons.Dtos.query.Paging;
import com.example.demo.commons.Dtos.query.QueryParam;
import com.example.demo.commons.sevices.StqService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController(value = "实体操作")
@Api(tags = "实体操作", description = "实体的增删改查")
public class StqController {
    @Autowired
    private StqService stq;

    @ApiOperation(value = "实体插入")
    @PostMapping("/app/stq/entity/insert")
    public Rtn<Boolean> dataInsert(@RequestParam String className, @RequestBody JSONObject dataItem)
            throws ClassNotFoundException {
        try {
            Object entity = JSONObject.toJavaObject(dataItem, Class.forName(className));
            boolean insert = stq.save(entity);
            return Rtn.Success(true);
        } catch (Exception e) {
            return Rtn.Error("唯一错误");
        }

    }

    @PostMapping("/app/stq/query")
    public Rtn<Paging> queryEntity(@RequestParam String className, @RequestBody QueryParam queryParam)
            throws ClassNotFoundException {
        Paging paging = stq.findPageEntity(queryParam, className);
        return Rtn.Success(paging);
    }

    // @PostMapping("/app/stq/entity/update")
    @PostMapping("/app/stq/entity/update")
    public Rtn<Boolean> dataUpdate(@RequestParam String className, @RequestBody JSONObject body)
            throws ClassNotFoundException {
        Object data = JSONObject.toJavaObject(body, Class.forName(className));
        Object result = stq.update(data);
        return Rtn.Success(true);
    }

    @PostMapping("/app/stq/entity/delete")
    public Rtn<Boolean> entityDelete(String className, @RequestBody JSONObject dataItem) throws ClassNotFoundException {
        Object entity = JSONObject.toJavaObject(dataItem, Class.forName(className));
        stq.delete(entity);
        return Rtn.Success(true);
    }

}
