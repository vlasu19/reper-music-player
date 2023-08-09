package com.xxnan.reper.mapper;

import com.xxnan.reper.pojo.entity.Consumer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConsumerMapper {
    @Select("select * from consumer")
    List<Consumer> selectList();

    @Select("select * from consumer where username=#{username}")
    Consumer getByUsername(String username);

    int insert(Consumer consumer);
}
