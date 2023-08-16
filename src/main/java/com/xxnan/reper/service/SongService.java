package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.SongDTO;
import com.xxnan.reper.pojo.entity.Song;

import java.util.List;

public interface SongService {
    void addSong(Song song);

    void deleteSong(Integer id);

    List<Song> allSong();

    Song songOfId(Integer id);

    List<Song> songOfSingerId(Integer singerId);

    List<Song> songOfSingerName(String name);

    void updateSongMsg(SongDTO songDTO);

    void updateSongPic(Song song);

    void updateSongUrl(Song song);
}
