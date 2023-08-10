package com.xxnan.reper.controller;


import com.xxnan.reper.common.result.R;
import com.xxnan.reper.pojo.DTO.CollectDTO;
import com.xxnan.reper.pojo.entity.Collect;
import com.xxnan.reper.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/collection")
@Api(tags = "收藏相关接口")
public class CollectController {

    @Autowired
    private CollectService collectService;


    // 添加收藏的歌曲
    //前台界面逻辑
    @PostMapping("/add")
    public R addCollection(@RequestBody CollectDTO collectDTO) {
        log.info("添加收藏：{}",collectDTO);
        collectService.addCollection(collectDTO);
        return R.success("添加成功");
    }

    //TODO  这些其实有点偏简单的逻辑  所以就一点 所以放在外面  拿到里面
    // 取消收藏的歌曲
    @DeleteMapping("/delete")
    @ApiOperation("取消收藏")
    public R deleteCollection(@RequestParam Integer userId, @RequestParam Integer songId) {
        log.info("取消用户{}音乐{}收藏",userId,songId);
        collectService.deleteCollect(userId, songId);
        return R.success("删除成功");
    }

    // 是否收藏歌曲
    @PostMapping("/status")
    @ApiOperation("查看是否收藏了该歌曲")
    public R isCollection(@RequestBody CollectDTO collectDTO) {
        log.info("是否收藏了歌曲：{}",collectDTO);
        Collect collect=collectService.existSongId(collectDTO);
        if(collect!=null){
            return R.success("已收藏", true);
        } else {
            return R.success("未收藏", false);
        }
    }

    // 返回的指定用户 ID 收藏的列表
    @GetMapping("/detail")
    @ApiOperation("查询用户收藏")
    public R collectionOfUser(@RequestParam Integer userId) {
        log.info("查询用户{}的收藏",userId);
        List<Collect> collections=collectService.collectionOfUser(userId);
        return R.success("用户收藏",collections);
    }
}
