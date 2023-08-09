package com.xxnan.reper.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ConsumerDTO implements Serializable {
    private Integer id;

    private String username;

//    private String oldPassword; //因为会用到用户旧密码 这无所谓的对应即可

    private String password;

    private Byte sex;

    private String phoneNum;

    private String email;

    private Date birth;

    private String introduction;

    private String location;

    private String avator;

//    private LocalDateTime createTime;
}
