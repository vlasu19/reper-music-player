package com.xxnan.reper.pojo.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CollectDTO {
    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer songId;

    private Integer songListId;

    private LocalDateTime createTime;
}
