package com.xxnan.reper.service.impl;

import com.xxnan.reper.mapper.RankListMapper;
import com.xxnan.reper.service.RankListService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class RankListServiceImpl implements RankListService {
    @Autowired
    private RankListMapper rankListMapper;
}
