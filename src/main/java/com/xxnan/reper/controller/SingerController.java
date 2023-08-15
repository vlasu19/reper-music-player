package com.xxnan.reper.controller;


import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.constant.PathConstant;
import com.xxnan.reper.common.result.R;
import com.xxnan.reper.common.utils.AliOssUtil;
import com.xxnan.reper.pojo.DTO.SingerDTO;
import com.xxnan.reper.pojo.entity.Singer;
import com.xxnan.reper.service.SingerService;
import io.lettuce.core.StrAlgoArgs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/singer")
@RestController
@Api(tags = "歌手相关接口")
public class SingerController {

    @Autowired
    private SingerService singerService;
    @Autowired
    private AliOssUtil aliOssUtil;

    // 添加歌手
    @PostMapping("/add")
    public R addSinger(@RequestBody SingerDTO singerDTO) {
        log.info("新增歌手：{}",singerDTO);
        singerService.addSinger(singerDTO);
        return R.success(null);
    }

    /**
     * 删除歌手的同时删除歌手的歌曲
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public R deleteSinger(@RequestParam Integer id) {
        log.info("删除歌手ID{}",id);
        singerService.deleteSinger(id);
        return R.success(null);
    }

    // 返回所有歌手
    @GetMapping
    public R allSinger() {
        log.info("查询所有歌手");
        List<Singer>singers = singerService.allSinger();
        return R.success(null,singers);
    }

    // 根据歌手名查找歌手
    @GetMapping("/name/detail")
    public R singerOfName(@RequestParam String name) {
        log.info("根据歌手名{}查询歌手",name);
        List<Singer>singers = singerService.singerOfName(name);
        return R.success(null,singers);
    }

    // 根据歌手性别查找歌手
    @GetMapping("/sex/detail")
    public R singerOfSex(@RequestParam Byte sex) {
        log.info("根据性别{}查询歌手",sex);
        List<Singer>singers = singerService.singerOfSex(sex);
        return R.success(null,singers);
    }

    // 更新歌手信息
    @PostMapping("/update")
    public R updateSingerMsg(@RequestBody SingerDTO singerDTO) {
        log.info("更新歌手信息：{}",singerDTO);
        singerService.updateSingerMsg(singerDTO);
        return R.success("修改成功");
    }

    // 更新歌手头像
    @PostMapping("/avatar/update")
    @ApiOperation("歌手照片上传")
    @Transactional
    public R updateSingerPic(@RequestParam MultipartFile file, @RequestParam Integer id) {
        log.info("更新歌手{}的头像：{}",id,file);
        String filePath;
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName= PathConstant.SINGER_PIC +UUID.randomUUID().toString()+suffix;
            filePath = aliOssUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return R.fatal(MessageConstant.UPLOAD_FAILED +e.getMessage());
        }
        Singer singer=Singer.builder().id(id).pic(filePath).build();
        singerService.updateSingerPic(singer);

        return R.success("修改成功");
    }
}
