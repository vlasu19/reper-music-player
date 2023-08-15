package com.xxnan.reper.pojo.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class SingerDTO {
    private Integer id;

    private String name;

    private Byte sex;

    private String pic;

    private Date birth;

    private String location;

    private String introduction;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
