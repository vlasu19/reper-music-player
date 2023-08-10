package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.CollectMapper;
import com.xxnan.reper.pojo.DTO.CollectDTO;
import com.xxnan.reper.pojo.entity.Collect;
import com.xxnan.reper.service.CollectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public void addCollection(CollectDTO collectDTO) {
        Collect collect=new Collect();
        BeanUtils.copyProperties(collectDTO,collect);
        int i=collectMapper.insert(collect);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.COLLECT_FAILED);
        }
    }

    @Override
    public void deleteCollect(Integer userId, Integer songId) {
        int i=collectMapper.delByUserAndSong(userId,songId);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_COLLECT_FAILED);
        }
    }

    @Override
    public Collect existSongId(CollectDTO collectDTO) {
        Integer userId=collectDTO.getUserId();
        Integer songId=collectDTO.getSongId();
        return collectMapper.getByUserAndSong(userId,songId);
    }

    @Override
    public List<Collect> collectionOfUser(Integer userId) {
        List<Collect>collections=collectMapper.getByUserId(userId);
        return collections;
    }
}
