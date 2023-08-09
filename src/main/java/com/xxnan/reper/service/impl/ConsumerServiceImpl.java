package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.AccountNotFoundException;
import com.xxnan.reper.common.exception.DuplicateUsernameException;
import com.xxnan.reper.common.exception.SQLFailedException;
import com.xxnan.reper.common.exception.PasswordErrorException;
import com.xxnan.reper.mapper.CollectMapper;
import com.xxnan.reper.mapper.ConsumerMapper;
import com.xxnan.reper.pojo.DTO.ConsumerDTO;
import com.xxnan.reper.pojo.entity.Consumer;
import com.xxnan.reper.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.xxnan.reper.common.constant.MD5Constant.SALT;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.selectList();
    }

    @Override
    public void addConsumer(ConsumerDTO consumerDTO) {
        //判断用户是否存在
        if(consumerMapper.getByUsername(consumerDTO.getUsername())!=null){
            throw new DuplicateUsernameException(MessageConstant.USERNAME_EXISTS);
        }
        //处理注册填充信息
        Consumer consumer=new Consumer();
        BeanUtils.copyProperties(consumerDTO,consumer);

        String password= DigestUtils.md5DigestAsHex((SALT+ consumerDTO.getPassword()).getBytes(StandardCharsets.UTF_8));
        consumer.setPassword(password);
        if (StringUtils.isBlank(consumer.getPhoneNum())) {
            consumer.setPhoneNum(null);
        }
        if (StringUtils.isBlank(consumer.getEmail())) {
            consumer.setEmail(null);
        }
        //TODO 放到服务器上后，这条语句可能会做修改
        consumer.setAvator("img/avatorImages/user.jpg");
        //插入
        //这里可能存在PhoneNum、Email重复错误，放在全局异常处理里面处理
        int i=consumerMapper.insert(consumer);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.SIGN_IN_FAILED);
        }
    }

    @Override
    public Consumer verityPassword(ConsumerDTO consumerDTO) {
        String password= DigestUtils.md5DigestAsHex((SALT+consumerDTO.getPassword()).getBytes(StandardCharsets.UTF_8));
        String username= consumerDTO.getUsername();
        //System.out.println("md5:"+password);
        Consumer consumer=consumerMapper.getByUsername(username);
        //账号不存在
        if(consumer==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //比较MD5加密后的值
        if(!password.equals(consumer.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return consumer;
    }

    @Override
    public Consumer userOfId(Integer id) {
        return consumerMapper.getById(id);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        //删除用户
        int i=consumerMapper.delById(id);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.DEL_FAILED);
        }
        //删除用户收藏,没有的话不删除
        collectMapper.delByUserId(id);
    }

    @Override
    public void updateUserMsg(ConsumerDTO consumerDTO) {
        Consumer consumer=new Consumer();
        BeanUtils.copyProperties(consumerDTO,consumer);
        int i=consumerMapper.update(consumer);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPDATE_FAILED);
        }
    }

    @Override
    public void updatePassword(ConsumerDTO consumerDTO) {
        //判断旧密码是否正确
        String old=DigestUtils.md5DigestAsHex((SALT+consumerDTO.getOldPassword()).getBytes(StandardCharsets.UTF_8));
        String neww=DigestUtils.md5DigestAsHex((SALT+consumerDTO.getPassword()).getBytes(StandardCharsets.UTF_8));
        String now=consumerMapper.getById(consumerDTO.getId()).getPassword();
        if(!old.equals(now)){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //修改密码
        Consumer consumer=Consumer.builder().id(consumerDTO.getId()).password(neww).build();
        int i=consumerMapper.update(consumer);
        if(i<=0){
            throw new SQLFailedException(MessageConstant.UPDATE_FAILED);
        }
    }
}
