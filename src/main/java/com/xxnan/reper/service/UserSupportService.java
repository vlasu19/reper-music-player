package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.UserSupportDTO;
import com.xxnan.reper.pojo.entity.UserSupport;

public interface UserSupportService {
    UserSupport isUserSupportComment(UserSupportDTO userSupportDTO);

    void insertCommentSupport(UserSupportDTO userSupportDTO);

    void deleteCommentSupport(UserSupportDTO userSupportDTO);
}
