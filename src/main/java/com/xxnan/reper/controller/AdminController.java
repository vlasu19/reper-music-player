package com.xxnan.reper.controller;

import com.xxnan.reper.common.constant.JwtClaimsConstant;
import com.xxnan.reper.common.properties.JwtProperties;
import com.xxnan.reper.common.result.R;
import com.xxnan.reper.common.utils.JwtUtil;
import com.xxnan.reper.pojo.VO.AdminLoginVO;
import com.xxnan.reper.pojo.entity.Admin;
import com.xxnan.reper.service.AdminService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;


    @PostMapping("/login/status")
    public R loginStatus(@RequestBody Admin admin){
        log.info("管理员登录：{}",admin);
        Admin a=adminService.verityPassword(admin);

        Map<String,Object>claims=new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID,a.getId());
        //创建jwtToken
        String token= JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        //log.info("token:{}",token);
        //封装
        AdminLoginVO adminLoginVO=AdminLoginVO.builder().id(admin.getId()).username(admin.getUsername()).token(token).build();
        return R.success("登录成功",adminLoginVO);
    }


}
