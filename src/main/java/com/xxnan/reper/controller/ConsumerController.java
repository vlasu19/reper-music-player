package com.xxnan.reper.controller;


import com.xxnan.reper.common.constant.JwtClaimsConstant;
import com.xxnan.reper.common.properties.JwtProperties;
import com.xxnan.reper.common.result.R;
import com.xxnan.reper.common.utils.JwtUtil;
import com.xxnan.reper.pojo.DTO.ConsumerDTO;
import com.xxnan.reper.pojo.VO.AdminLoginVO;
import com.xxnan.reper.pojo.VO.ConsumerLoginVO;
import com.xxnan.reper.pojo.entity.Admin;
import com.xxnan.reper.pojo.entity.Consumer;
import com.xxnan.reper.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping
    @ApiOperation("查询所有用户")
    public R allUser() {
        List<Consumer>consumers = consumerService.allUser();
        return R.success("null",consumers);
    }

    @PostMapping("/add")
    @ApiOperation("注册")
    public R addUser(@RequestBody ConsumerDTO consumerDTO){
        log.info("新增用户：{}", consumerDTO);
        consumerService.addConsumer(consumerDTO);
        return R.success("注册成功");
    }

    @PostMapping("/login/status")
    @ApiOperation("登录")
    public R loginStatus(@RequestBody ConsumerDTO consumerDTO){
        log.info("用户登录：{}",consumerDTO);
        Consumer consumer=consumerService.verityPassword(consumerDTO);

        Map<String,Object> claims=new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,consumer.getId());
        //创建jwtToken
        String token= JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        //log.info("token:{}",token);
        //封装VO
        ConsumerLoginVO consumerLoginVO=new ConsumerLoginVO();
        BeanUtils.copyProperties(consumer,consumerLoginVO);
        consumerLoginVO.setToken(token);

        return R.success("登录成功",consumerLoginVO);
    }

    /**
     * 用户界面调用
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("根据ID查询用户")
    public R userOfId(@RequestParam Integer id) {
        log.info("根据ID{}查询用户",id);
        Consumer consumer=consumerService.userOfId(id);
        return R.success(null,consumer);
    }

    /**
     * TODO
     * 目前批量删除是多次调用此接口，未来可能考虑一次请求传入多个ID进行批量删除
     *
     * 目前事务认定为：用户删除后收藏也删除，而评论和点赞留下
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    @ApiOperation("管理员删除用户")
    public R deleteUser(@RequestParam Integer id) {
        log.info("删除用户ID：{}",id);
        consumerService.deleteUser(id);
        return R.success("删除成功");
    }

    @PostMapping("/update")
    @ApiOperation("修改用户信息")
    public R updateUserMsg(@RequestBody ConsumerDTO consumerDTO) {
        log.info("修改用户信息：{}",consumerDTO);
        consumerService.updateUserMsg(consumerDTO);
        return R.success("修改成功");
    }

    @PostMapping("/updatePassword")
    @ApiOperation("修改密码")
    public R updatePassword(@RequestBody ConsumerDTO consumerDTO) {
        log.info("修改密码：{}",consumerDTO);
        consumerService.updatePassword(consumerDTO);
        return R.success("修改成功");
    }


}
