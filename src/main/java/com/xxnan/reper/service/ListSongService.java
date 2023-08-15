package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.ListSongDTO;
import com.xxnan.reper.pojo.entity.ListSong;
import com.xxnan.reper.pojo.entity.Song;

import java.util.List;

public interface ListSongService {
    void addListSong(ListSongDTO listSongDTO);

    void deleteListSong(Integer songId);

    List<ListSong> listSongOfSongId(Integer songListId);

    void updateListSongMsg(ListSongDTO listSongDTO);
}
