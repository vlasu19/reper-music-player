package com.xxnan.reper.mapper;

import com.xxnan.reper.annotation.AutoFill;
import com.xxnan.reper.common.enumeration.OperationType;
import com.xxnan.reper.pojo.entity.Consumer;
import org.apache.ibatis.annotations.Delete;
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

    @AutoFill(OperationType.INSERT)
    int insert(Consumer consumer);

    @Select("select * from consumer where id=#{id}")
    Consumer getById(Integer id);

    @Delete("delete from consumer where id=#{id}")
    int delById(Integer id);

    @AutoFill(OperationType.UPDATE)
    int update(Consumer consumer);
}
