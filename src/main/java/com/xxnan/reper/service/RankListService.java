package com.xxnan.reper.service;


import com.xxnan.reper.pojo.DTO.RankListDTO;
import io.swagger.models.auth.In;

public interface RankListService {
    void addRank(RankListDTO rankListDTO);

    Integer rankOfSongListId(Integer songListId);

    Integer getUserRank(Integer consumerId, Integer songListId);
}
