package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.ListSongMapper;
import com.xxnan.reper.pojo.DTO.ListSongDTO;
import com.xxnan.reper.pojo.entity.ListSong;
import com.xxnan.reper.pojo.entity.Song;
import com.xxnan.reper.service.ListSongService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public void addListSong(ListSongDTO listSongDTO) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(listSongDTO, listSong);
        int i=listSongMapper.insert(listSong);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.INSERT_FAILED);
        }
    }

    @Override
    public void deleteListSong(Integer songId) {
        int i=listSongMapper.delBySongId(songId);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_FAILED);
        }
    }

    @Override
    public List<ListSong> listSongOfSongId(Integer songListId) {
        return listSongMapper.getBySongListId(songListId);
    }

    @Override
    public void updateListSongMsg(ListSongDTO listSongDTO) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(listSongDTO, listSong);
        int i=listSongMapper.update(listSong);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPDATE_FAILED);
        }
    }
}
