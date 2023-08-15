package com.xxnan.reper.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SongMapper {
    @Delete("delete from song where singer_id=#{singerId}")
    int delBySingerId(Integer singerId);
}
