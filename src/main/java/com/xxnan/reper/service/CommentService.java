package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.CommentDTO;
import com.xxnan.reper.pojo.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(CommentDTO commentDTO);

    void deleteComment(Integer id);

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);

    void updateCommentMsg(CommentDTO commentDTO);
}
