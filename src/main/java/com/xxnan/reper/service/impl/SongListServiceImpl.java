package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.constant.PathConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.ListSongMapper;
import com.xxnan.reper.mapper.SongListMapper;
import com.xxnan.reper.mapper.SongMapper;
import com.xxnan.reper.pojo.DTO.SongListDTO;
import com.xxnan.reper.pojo.entity.ListSong;
import com.xxnan.reper.pojo.entity.SongList;
import com.xxnan.reper.service.SongListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapper songListMapper;
    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public void addSongList(SongListDTO songListDTO) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(songListDTO, songList);
        songList.setPic(PathConstant.DEFAULT_PIC);
        int i=songListMapper.insert(songList);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.INSERT_FAILED);
        }
    }

    @Override
    @Transactional
    public void deleteSongList(Integer id) {
        int i=songListMapper.delById(id);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_FAILED);
        }
        List<ListSong>listSongs= listSongMapper.getBySongListId(id);
        if(listSongs.size()>0) {
            i=listSongMapper.delBySongListId(id);
            if(i<=0){ throw new SQLFailedException(MessageConstant.DEL_FAILED);}
        }
    }

    @Override
    public List<SongList> allSongList() {
        return songListMapper.criteriaQuery(null,null);
    }

    @Override
    public List<SongList> likeTitle(String title) {
        return songListMapper.criteriaQuery(title,null);
    }

    @Override
    public List<SongList> likeStyle(String style) {
        return songListMapper.criteriaQuery(null,style);
    }

    @Override
    public void updateSongListMsg(SongListDTO songListDTO) {
        SongList songList=new SongList();
        BeanUtils.copyProperties(songListDTO,songList);
        int i=songListMapper.update(songList);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPLOAD_FAILED);
        }
    }

    @Override
    public void updateSongListImg(SongList songList) {
        int i=songListMapper.update(songList);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPLOAD_FAILED);
        }
    }
}
