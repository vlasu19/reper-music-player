package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.ListSongMapper;
import com.xxnan.reper.mapper.SongMapper;
import com.xxnan.reper.pojo.DTO.SongDTO;
import com.xxnan.reper.pojo.entity.Song;
import com.xxnan.reper.service.SongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;
    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public void addSong(Song song) {
        int i=songMapper.insert(song);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.INSERT_FAILED);
        }
    }

    @Override
    @Transactional
    public void deleteSong(Integer id) {
        int i=songMapper.delById(id);
        if(i<=0)throw new SQLFailedException(MessageConstant.DEL_FAILED);
        i=listSongMapper.delBySongId(id);
        if(i<=0)throw new SQLFailedException(MessageConstant.DEL_FAILED);
    }

    @Override
    public List<Song> allSong() {
        return songMapper.criteriaQuery(null,null);
    }

    @Override
    public List<Song> songOfId(Integer id) {
        return songMapper.getById(id);
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songMapper.criteriaQuery(singerId,null);
    }

    @Override
    public List<Song> songOfSingerName(String name) {
        return songMapper.criteriaQuery(null,name);
    }

    @Override
    public void updateSongMsg(SongDTO songDTO) {
        Song song=new Song();
        BeanUtils.copyProperties(songDTO,song);
        int i=songMapper.update(song);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPDATE_FAILED);
        }
    }

    @Override
    public void updateSongPic(Song song) {
        int i=songMapper.update(song);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPLOAD_FAILED);
        }
    }

    @Override
    public void updateSongUrl(Song song) {
        int i=songMapper.update(song);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPLOAD_FAILED);
        }
    }
}
