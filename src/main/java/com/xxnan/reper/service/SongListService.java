package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.SongListDTO;
import com.xxnan.reper.pojo.entity.SongList;

import java.util.List;

public interface SongListService {
    void addSongList(SongListDTO songListDTO);

    void deleteSongList(Integer id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);

    void updateSongListMsg(SongListDTO songListDTO);

    void updateSongListImg(SongList songList);
}
