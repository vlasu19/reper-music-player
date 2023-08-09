package com.xxnan.reper.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectMapper {

    @Delete("delete from collect where user_id=#{userId}")
    void delByUserId(Integer userId);
}
