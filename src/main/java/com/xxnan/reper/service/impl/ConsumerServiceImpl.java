package com.xxnan.reper.service.impl;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.exception.DuplicateUsernameException;
import com.xxnan.reper.common.exception.InsertFailedException;
import com.xxnan.reper.mapper.ConsumerMapper;
import com.xxnan.reper.pojo.DTO.ConsumerDTO;
import com.xxnan.reper.pojo.entity.Consumer;
import com.xxnan.reper.service.ConsumerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.xxnan.reper.common.constant.MD5Constant.SALT;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

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
            throw new InsertFailedException("注册失败");
        }
    }

    @Override
    public Consumer verityPassword(ConsumerDTO consumerDTO) {

        return null;
    }
}
