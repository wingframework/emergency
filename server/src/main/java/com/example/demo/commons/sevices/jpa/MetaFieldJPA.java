package com.example.demo.commons.sevices.jpa;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.demo.commons.entitys.queryTemplate.MetaField;
import com.example.demo.commons.sevices.jpa.base.BaseRepository;

public interface MetaFieldJPA extends BaseRepository<MetaField, Integer> {

    /**
     * 根据mataobject中的objectcode 得到字段列表
     **/
    // @Cacheable(value="metafile",key = "#p0")
    List<MetaField> findByObjectCode(String objCode);

    @Transactional
    // @CacheEvict(value = "metafile",key = "#p0")
    void deleteByObjectCode(String objectCode);
    // @Transactional
    // void deleteByMetaId(Integer metaId);
}
