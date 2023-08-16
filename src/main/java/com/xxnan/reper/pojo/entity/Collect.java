package com.xxnan.reper.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

//@TableName(value = "collect")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collect implements Serializable {
//    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Byte type;

    private Integer songId;

    private Integer songListId;

//    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
