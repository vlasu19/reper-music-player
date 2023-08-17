package com.xxnan.reper.pojo.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class SingerDTO {
    private Integer id;

    private String name;

    private Byte sex;

    private String pic;

    private LocalDate birth;

    private String location;

    private String introduction;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
