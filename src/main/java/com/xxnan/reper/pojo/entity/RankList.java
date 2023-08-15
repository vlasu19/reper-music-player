package com.xxnan.reper.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@TableName(value = "rank_list")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankList implements Serializable {

//    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer songListId;

    private Integer consumerId;

    private Integer score;

}
