package com.xxnan.reper.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@TableName(value = "singer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Singer implements Serializable {

//    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Byte sex;

    private String pic;

    private LocalDate birth;

    private String location;

    private String introduction;

//    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

//    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
