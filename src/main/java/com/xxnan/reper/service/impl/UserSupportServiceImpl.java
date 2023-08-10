package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.mapper.UserSupportMapper;
import com.xxnan.reper.pojo.DTO.UserSupportDTO;
import com.xxnan.reper.pojo.entity.UserSupport;
import com.xxnan.reper.service.UserSupportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSupportServiceImpl implements UserSupportService {
    @Autowired
    private UserSupportMapper userSupportMapper;

    @Override
    public UserSupport isUserSupportComment(UserSupportDTO userSupportDTO) {
        Integer userId=userSupportDTO.getUserId();
        Integer commentId=userSupportDTO.getCommentId();
        return userSupportMapper.getByUserAndComment(userId,commentId);
    }

    @Override
    public void insertCommentSupport(UserSupportDTO userSupportDTO) {
        UserSupport userSupport=new UserSupport();
        BeanUtils.copyProperties(userSupportDTO,userSupport);
        int i=userSupportMapper.insert(userSupport);
        if(i<=0){
            throw new SQLFailedException("点赞失败");
        }
    }

    @Override
    public void deleteCommentSupport(UserSupportDTO userSupportDTO) {
        Integer userId=userSupportDTO.getUserId();
        Integer commentId=userSupportDTO.getCommentId();
        int i=userSupportMapper.delByUserAndComment(userId,commentId);
        if(i<=0){
            throw new SQLFailedException("取消点赞失败");
        }
    }
}
