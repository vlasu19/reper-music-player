package com.xxnan.reper.mapper;

import com.xxnan.reper.annotation.AutoFill;
import com.xxnan.reper.common.enumeration.OperationType;
import com.xxnan.reper.pojo.entity.ListSong;
import com.xxnan.reper.pojo.entity.Song;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ListSongMapper {
    @Delete("delete from list_song where song_list_id=#{songListId}")
    int delBySongListId(Integer songListId);

    @Insert("insert into list_song(song_id, song_list_id) values (#{songId},#{songListId})")
//    @AutoFill(OperationType.INSERT)
    int insert(ListSong listSong);

    @Delete("delete from list_song where song_id=#{songId} and song_list_id=#{songListId}")
    int delBySongAndSList(Integer songId,Integer songListId);

    @Select("select * from list_song where song_list_id=#{songListId} order by song_id")
    List<ListSong> getBySongListId(Integer songListId);

//    @AutoFill(OperationType.UPDATE)
    int update(ListSong listSong);

    @Delete("delete from list_song where song_id=#{songId}")
    int delBySongId(Integer id);

    @Select("select * from list_song where song_id=#{songId}")
    List<ListSong> getBySongId(Integer songId);
}
