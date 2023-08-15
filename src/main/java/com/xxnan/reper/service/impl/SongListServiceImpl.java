package com.xxnan.reper.service.impl;

import com.xxnan.reper.mapper.SongListMapper;
import com.xxnan.reper.mapper.SongMapper;
import com.xxnan.reper.service.SongListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapper songListMapper;
    @Autowired
    private SongMapper songMapper;
}
