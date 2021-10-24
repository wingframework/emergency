package com.example.demo.commons.mappers;

import com.example.demo.passport.Dto.QuerySentSmsNotExpire;
import com.example.demo.passport.entitys.Sms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SmsMapper {
    void Create( @Param("sms") Sms sms );

      // 这个方法的名字总感觉怪怪的，改成什么比较好呢？
                         // Expire 是失效、到期的意思
    List<Sms> querySentSmsExpire(QuerySentSmsNotExpire querySentSmsExpire);




}
