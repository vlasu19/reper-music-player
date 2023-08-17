package com.xxnan.reper.pojo.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentDTO {
    private Integer id;

    private Integer userId;

    private Integer songId;

    private Integer songListId;

    private String content;

    private LocalDateTime createTime;

    private Byte nowType;

    private Integer up;//点赞
}
