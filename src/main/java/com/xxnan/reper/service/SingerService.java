package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.SingerDTO;
import com.xxnan.reper.pojo.entity.Singer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SingerService {
    void addSinger(SingerDTO singerDTO);

    void deleteSinger(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Byte sex);

    void updateSingerMsg(SingerDTO singerDTO);

    void updateSingerPic(Singer singer);
}
