package com.xxnan.reper.mapper;

import com.xxnan.reper.pojo.entity.UserSupport;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserSupportMapper {
    @Select("select * from user_support where user_id=#{userId} and comment_id=#{commentId}")
    UserSupport getByUserAndComment(Integer userId, Integer commentId);

    @Insert("insert into user_support(comment_id, user_id) VALUES (#{userId},#{commentId})")
    int insert(UserSupport userSupport);

    @Delete("delete from user_support where user_id=#{userId} and comment_id=#{commentId}")
    int delByUserAndComment(Integer userId, Integer commentId);

    @Delete("delete from user_support where comment_id=#{commentId}")
    int delByCommentId(Integer commentId);
}
