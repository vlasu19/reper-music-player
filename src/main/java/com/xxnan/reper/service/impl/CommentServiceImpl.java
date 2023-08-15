package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.CollectMapper;
import com.xxnan.reper.mapper.CommentMapper;
import com.xxnan.reper.mapper.UserSupportMapper;
import com.xxnan.reper.pojo.DTO.CommentDTO;
import com.xxnan.reper.pojo.entity.Comment;
import com.xxnan.reper.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserSupportMapper userSupportMapper;

    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setType(commentDTO.getNowType());
        int i=commentMapper.insert(comment);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.COMMENT_FAILED);
        }
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        return commentMapper.getBySongId(songId);
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        return commentMapper.getBySongListId(songListId);
    }

    @Override
    public void updateCommentMsg(CommentDTO commentDTO) {
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        int i=commentMapper.update(comment);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPDATE_FAILED);
        }
    }

    @Override
    @Transactional
    public void deleteComment(Integer id) {
        int i=commentMapper.delById(id);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_FAILED);
        }
        i=userSupportMapper.delByCommentId(id);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_FAILED);
        }
    }
}
