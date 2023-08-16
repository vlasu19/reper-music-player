package com.xxnan.reper.mapper;

import com.xxnan.reper.annotation.AutoFill;
import com.xxnan.reper.common.enumeration.OperationType;
import com.xxnan.reper.pojo.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectMapper {

    @Delete("delete from collect where user_id=#{userId}")
    void delByUserId(Integer userId);

    @AutoFill(OperationType.INSERT)
    int insert(Collect collect);

    @Delete("delete from collect where user_id=#{userId} and song_id=#{songId}")
    int delByUserAndSong(Integer userId, Integer songId);

    @Select("select * from collect where user_id=#{userId} and song_id=#{songId}")
    Collect getByUserAndSong(Integer userId, Integer songId);

    @Select("select * from collect where user_id=#{userId}")
    List<Collect> getByUserId(Integer userId);
}
