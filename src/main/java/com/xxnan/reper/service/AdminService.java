package com.xxnan.reper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxnan.reper.pojo.entity.Admin;

public interface AdminService {
    Admin verityPassword(Admin admin);
}
