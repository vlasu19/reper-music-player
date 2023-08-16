package com.xxnan.reper.mapper;

import com.xxnan.reper.annotation.AutoFill;
import com.xxnan.reper.common.enumeration.OperationType;
import com.xxnan.reper.pojo.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(user_id, song_id, song_list_id, content, create_time, type, up) VALUES " +
            "(#{userId},#{songId},#{songListId},#{content},#{createTime},#{type},#{up})")
    @AutoFill(OperationType.INSERT)
    int insert(Comment comment);

    @Select("select * from comment where song_id=#{songId}")
    List<Comment> getBySongId(Integer songId);

    @Select("select * from comment where song_list_id=#{songListId}")
    List<Comment> getBySongListId(Integer songListId);

    @AutoFill(OperationType.UPDATE)
    int update(Comment comment);

    @Delete("delete from comment where id=#{id}")
    int delById(Integer id);
}
