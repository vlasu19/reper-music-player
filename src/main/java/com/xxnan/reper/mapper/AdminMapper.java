package com.xxnan.reper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxnan.reper.pojo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where name=#{name}")
    Admin getByUsername(String name);
}
