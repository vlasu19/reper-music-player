package com.xxnan.reper.mapper;

import com.xxnan.reper.pojo.entity.Song;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SongMapper {
    @Delete("delete from song where singer_id=#{singerId}")
    int delBySingerId(Integer singerId);

    int insert(Song song);

    @Delete("delete from song where id=#{id}")
    int delById(Integer id);

    List<Song> criteriaQuery(Integer singerId, String name);

    @Select("select * from song where id=#{id}")
    Song getById(Integer id);

    int update(Song song);
}
