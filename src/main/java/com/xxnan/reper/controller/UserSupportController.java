package com.xxnan.reper.controller;


import com.xxnan.reper.common.result.R;
import com.xxnan.reper.pojo.DTO.UserSupportDTO;
import com.xxnan.reper.pojo.entity.UserSupport;
import com.xxnan.reper.service.UserSupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/userSupport")
@Api(tags = "点赞相关接口")
public class UserSupportController {

    @Autowired
    private UserSupportService userSupportService;

    @PostMapping("/test")
    @ApiOperation("查看是否已点赞")
    public R isUserSupportComment(@RequestBody UserSupportDTO userSupportDTO) {
        log.info("查看是否已点赞：{}",userSupportDTO);
        UserSupport userSupport=userSupportService.isUserSupportComment(userSupportDTO);
        if (userSupport!=null) {
            return R.success("您已取消点赞", true);
        } else {
            return R.success("您已点赞", false);
        }
    }

    @PostMapping("/insert")
    @ApiOperation("点赞")
    public R insertCommentSupport(@RequestBody UserSupportDTO userSupportDTO) {
        log.info("点赞：{}",userSupportDTO);
        userSupportService.insertCommentSupport(userSupportDTO);
        return R.success("点赞成功");
    }

    @PostMapping("/delete")
    @ApiOperation("取消点赞")
    public R deleteCommentSupport(@RequestBody UserSupportDTO userSupportDTO) {
        log.info("取消点赞：{}",userSupportDTO);
        userSupportService.deleteCommentSupport(userSupportDTO);
        return R.success("取消点赞成功");
    }
}
