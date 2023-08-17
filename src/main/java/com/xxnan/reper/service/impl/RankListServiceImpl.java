package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.RankListMapper;
import com.xxnan.reper.pojo.DTO.RankListDTO;
import com.xxnan.reper.pojo.entity.RankList;
import com.xxnan.reper.service.RankListService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankListServiceImpl implements RankListService {
    @Autowired
    private RankListMapper rankListMapper;

    @Override
    public void addRank(RankListDTO rankListDTO) {
        RankList rankList = new RankList();
        BeanUtils.copyProperties(rankListDTO, rankList);
        int i=rankListMapper.insert(rankList);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.INSERT_FAILED);
        }
    }

    @Override
    public Integer rankOfSongListId(Integer songListId) {
        List<RankList>rankLists=rankListMapper.getBySongListId(songListId);
        Integer sum=0;
        Integer rankNum=rankLists.size();
        for (RankList rankList : rankLists) {
            sum+=rankList.getScore();
        }
        return (rankNum>0) ? sum/rankNum : 0;
    }

    @Override
    public Integer getUserRank(Integer consumerId, Integer songListId) {
        int i = rankListMapper.getScore(consumerId,songListId);
        return i;
    }
}
