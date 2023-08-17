package com.xxnan.reper.pojo.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;


@Data
public class SongDTO {

    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String pic;

    private String lyric;

    private String url;
}
