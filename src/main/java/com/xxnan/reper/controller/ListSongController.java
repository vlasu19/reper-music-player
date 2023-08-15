package com.xxnan.reper.controller;

import com.xxnan.reper.common.result.R;
import com.xxnan.reper.pojo.DTO.ListSongDTO;
import com.xxnan.reper.service.ListSongService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/listSong")
@RestController
@Api(tags = "歌单管理歌曲 接口")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;



    @PostMapping("/add")
    public R addListSong(@RequestBody ListSongDTO listSongDTO) {
        log.info("给歌单添加歌曲:{}",listSongDTO);
        listSongService.addListSong(listSongDTO);
        return R.success("添加成功");
    }

    @GetMapping("/delete")
    public R deleteListSong(@RequestParam Integer songId) {
        log.info("删除歌单歌曲，歌曲ID{}",songId);
        listSongService.deleteListSong(songId);
        return R.success("删除成功");
    }

    @GetMapping("/detail")
    public R listSongOfSongId(@RequestParam Integer songListId) {
        log.info("根据歌单{}返回歌曲歌单记录",songListId);
        return R.success(null,listSongService.listSongOfSongId(songListId));
    }

    // 更新歌单里面的歌曲信息
    @PostMapping("/update")
    public R updateListSongMsg(@RequestBody ListSongDTO listSongDTO) {
        log.info("更新歌单里面的歌曲信息:{}",listSongDTO);
        listSongService.updateListSongMsg(listSongDTO);
        return R.success("更新成功");
    }
}
