package com.xxnan.reper.pojo.DTO;

import lombok.Data;

import java.util.Date;


@Data
public class SongDTO {

    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    private Date createTime;

    private Date updateTime;

    private String pic;

    private String lyric;

    private String url;
}
