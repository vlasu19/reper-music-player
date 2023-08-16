package com.xxnan.reper.mapper;

import com.xxnan.reper.annotation.AutoFill;
import com.xxnan.reper.common.enumeration.OperationType;
import com.xxnan.reper.pojo.entity.Singer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingerMapper {
    @AutoFill(OperationType.INSERT)
    int insert(Singer singer);

    @Delete("delete from singer where id=#{id}")
    int delById(Integer id);

    List<Singer> criteriaQuery(String name, Byte sex);

    @AutoFill(OperationType.UPDATE)
    int update(Singer singer);
}
