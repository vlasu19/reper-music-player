package com.xxnan.reper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.AccountNotFoundException;
import com.xxnan.reper.common.exception.PasswordErrorException;
import com.xxnan.reper.mapper.AdminMapper;
import com.xxnan.reper.pojo.entity.Admin;
import com.xxnan.reper.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static com.xxnan.reper.common.constant.MD5Constant.SALT;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin verityPassword(Admin admin) {
        String password= DigestUtils.md5DigestAsHex((SALT+admin.getPassword()).getBytes(StandardCharsets.UTF_8));
        String username= admin.getUsername();
        //System.out.println("md5:"+password);
        Admin a=adminMapper.getByUsername(username);
        //账号不存在
        if(a==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //比较MD5加密后的值
        if(!password.equals(a.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return a;
    }
}
