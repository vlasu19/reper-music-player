package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.constant.PathConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.SingerMapper;
import com.xxnan.reper.mapper.SongMapper;
import com.xxnan.reper.pojo.DTO.SingerDTO;
import com.xxnan.reper.pojo.entity.Singer;
import com.xxnan.reper.pojo.entity.Song;
import com.xxnan.reper.service.SingerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;
    @Autowired
    private SongMapper songMapper;

    @Override
    public void addSinger(SingerDTO singerDTO) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(singerDTO, singer);
        singer.setPic(PathConstant.DEFAULT_PIC);
        int i=singerMapper.insert(singer);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.INSERT_FAILED);
        }
    }

    @Override
    @Transactional
    public void deleteSinger(Integer id) {
        int i=singerMapper.delById(id);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_FAILED);
        }
        List<Song>songs= songMapper.criteriaQuery(id,null);
        if(songs.size()>0) {
            i = songMapper.delBySingerId(id);
            if (i <= 0) {
                throw new SQLFailedException(MessageConstant.DEL_FAILED);
            }
        }
    }

    @Override
    public List<Singer> allSinger() {
        return singerMapper.criteriaQuery(null,null);
    }

    @Override
    public List<Singer> singerOfName(String name) {
        return singerMapper.criteriaQuery(name,null);
    }

    @Override
    public List<Singer> singerOfSex(Byte sex) {
        return singerMapper.criteriaQuery(null,sex);
    }

    @Override
    public void updateSingerMsg(SingerDTO singerDTO) {
        Singer singer=new Singer();
        BeanUtils.copyProperties(singerDTO,singer);
        int i=singerMapper.update(singer);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPDATE_FAILED);
        }
    }

    @Override
    public void updateSingerPic(Singer singer) {
        int i= singerMapper.update(singer);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPLOAD_FAILED);
        }
    }
}
