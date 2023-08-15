package com.xxnan.reper.mapper;

import com.xxnan.reper.pojo.entity.Singer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingerMapper {

    int insert(Singer singer);

    @Delete("delete from singer where id=#{id}")
    int delById(Integer id);

    List<Singer> criteriaQuery(String name, Byte sex);

    int update(Singer singer);
}
