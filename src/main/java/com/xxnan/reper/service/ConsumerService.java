package com.xxnan.reper.service;

import com.xxnan.reper.pojo.DTO.ConsumerDTO;
import com.xxnan.reper.pojo.entity.Consumer;

import java.util.List;

public interface ConsumerService {
    List<Consumer> allUser();

    void addConsumer(ConsumerDTO consumerDTO);

    Consumer verityPassword(ConsumerDTO consumerDTO);
}