package com.xxnan.reper.mapper;

import com.xxnan.reper.pojo.entity.RankList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankListMapper {
    @Insert("insert into rank_list(song_list_id, consumer_id, score) values (#{songListId},#{consumerId},#{score})")
    int insert(RankList rankList);

    @Select("select * from rank_list where song_list_id=#{songListId}")
    List<RankList> getBySongListId(Integer songListId);

    @Select("select score from rank_list where song_list_id=#{songListId} and consumer_id=#{consumerId}")
    Integer getScore(Integer consumerId, Integer songListId);
}
