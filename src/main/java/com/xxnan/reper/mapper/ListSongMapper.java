package com.xxnan.reper.mapper;

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

    @Insert("insert into list_song(song_id, song_list_id) values (#{songId},#{listSongId})")
    int insert(ListSong listSong);

    @Delete("delete from list_song where song_id=#{songId}")
    int delBySongId(Integer songId);

    @Select("select * from list_song where song_list_id=#{songListId}")
    List<ListSong> getBySongListId(Integer songListId);

    int update(ListSong listSong);
}
