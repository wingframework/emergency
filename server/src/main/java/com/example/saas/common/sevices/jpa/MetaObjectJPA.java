package com.example.saas.common.sevices.jpa;

import com.example.saas.common.entitys.queryTemplate.MetaObject;
import com.example.saas.common.sevices.jpa.base.BaseRepository;

import org.springframework.transaction.annotation.Transactional;

public interface MetaObjectJPA extends BaseRepository<MetaObject, Integer> {
    /** 根据查询名称对应code得到 元对象 **/
    // @Cacheable(value = "metaObject",key = "#p0")
    MetaObject findByObjectCode(String objectCode);

    @Transactional
    // @CacheEvict(value = "metaObject",key = "#p0")
    void deleteByObjectCode(String objectCode);

    // @CachePut(value = "metaObject",key = "#metaObject.objectCode")
    MetaObject saveAndFlush(MetaObject metaObject);

    // @CachePut(value = "metaObject",key = "#metaObject.objectCode")
    MetaObject save(MetaObject metaObject);
}
