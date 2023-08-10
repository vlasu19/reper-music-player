package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.CollectDTO;
import com.xxnan.reper.pojo.entity.Collect;

import java.util.List;

public interface CollectService {
    void addCollection(CollectDTO collectDTO);

    void deleteCollect(Integer userId, Integer songId);

    Collect existSongId(CollectDTO collectDTO);

    List<Collect> collectionOfUser(Integer userId);
}
