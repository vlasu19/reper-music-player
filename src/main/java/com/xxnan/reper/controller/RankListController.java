package com.xxnan.reper.controller;

import com.xxnan.reper.common.result.R;
import com.xxnan.reper.pojo.DTO.RankListDTO;
import com.xxnan.reper.service.RankListService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/rankList")
@RestController
public class RankListController {

    @Autowired
    private RankListService rankListService;


    // 提交评分
    @PostMapping("/add")
    public R addRank(@RequestBody RankListDTO rankListDTO) {
        log.info("提交评分{}",rankListDTO);
        rankListService.addRank(rankListDTO);
        return R.success("评分成功");
    }

    // 获取指定歌单的评分
    @GetMapping
    public R rankOfSongListId(@RequestParam Integer songListId) {
        log.info("获取歌单{}的评分",songListId);
        return R.success(null,rankListService.rankOfSongListId(songListId));
    }

    // 获取指定用户的歌单评分
    @GetMapping("/user")
    public R getUserRank(@RequestParam Integer consumerId, @RequestParam Integer songListId) {
        log.info("用户ID{}，歌单ID{}",consumerId,songListId);
        return R.success(null,rankListService.getUserRank(consumerId, songListId));
    }
}
