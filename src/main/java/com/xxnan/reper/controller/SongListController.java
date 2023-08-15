package com.xxnan.reper.controller;


import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.constant.PathConstant;
import com.xxnan.reper.common.result.R;
import com.xxnan.reper.common.utils.AliOssUtil;
import com.xxnan.reper.pojo.DTO.SongListDTO;
import com.xxnan.reper.pojo.entity.Singer;
import com.xxnan.reper.pojo.entity.SongList;
import com.xxnan.reper.service.SongListService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequestMapping("/songList")
@RestController
@Api(tags = "歌单相关接口")
public class SongListController {

    @Autowired
    private SongListService songListService;
    @Autowired
    private AliOssUtil aliOssUtil;

    // 添加歌单
    @PostMapping("/add")
    public R addSongList(@RequestBody SongListDTO songListDTO) {
        log.info("添加歌单：{}",songListDTO);
        songListService.addSongList(songListDTO);
        return R.success("添加成功");
    }

    // 删除歌单
    @GetMapping("/delete")
    public R deleteSongList(@RequestParam Integer id) {
        log.info("删除歌单ID{}",id);
        songListService.deleteSongList(id);
        return R.success("删除成功");
    }

    //TODO 这块就是前端显现相应的歌单list
    // 返回所有歌单
    @GetMapping
    public R allSongList() {
        log.info("返回所有歌单");
        return R.success(null,songListService.allSongList());
    }

    // 返回标题包含文字的歌单
    @GetMapping("/likeTitle/detail")
    public R songListOfLikeTitle(@RequestParam String title) {
        log.info("返回标题包含{}的歌单",title);
        return R.success(null,songListService.likeTitle(title));
    }

    // 返回指定类型的歌单
    @GetMapping("/style/detail")
    public R songListOfStyle(@RequestParam String style) {
        log.info("返回指定类型为{}的歌单",style);
        return R.success(null,songListService.likeStyle(style));
    }

    // 更新歌单信息
    @PostMapping("/update")
    public R updateSongListMsg(@RequestBody SongListDTO songListDTO) {
        log.info("更新歌单:{}",songListDTO);
        songListService.updateSongListMsg(songListDTO);
        return R.success("修改成功");
    }

    // 更新歌单图片
    @PostMapping("/img/update")
    @Transactional
    public R updateSongListPic(@RequestParam MultipartFile file, @RequestParam Integer id) {
        log.info("更新歌单{}的图片：{}",id,file);
        String filePath;
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName= PathConstant.SONGLIST_PIC + UUID.randomUUID().toString()+suffix;
            filePath = aliOssUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return R.fatal(MessageConstant.UPLOAD_FAILED +e.getMessage());
        }
        SongList songList=SongList.builder().id(id).pic(filePath).build();
        songListService.updateSongListImg(songList);
        return R.success("修改成功");
    }
}
