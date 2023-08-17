package com.xxnan.reper.controller;

import com.xxnan.reper.common.constant.MessageConstant;
import com.xxnan.reper.common.constant.PathConstant;
import com.xxnan.reper.common.result.R;
import com.xxnan.reper.common.utils.AliOssUtil;
import com.xxnan.reper.pojo.DTO.SongDTO;
import com.xxnan.reper.pojo.entity.Song;
import com.xxnan.reper.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequestMapping("/song")
@RestController
public class SongController {

    @Autowired
    private SongService songService;
    @Autowired
    private AliOssUtil aliOssUtil;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }


    // 添加歌曲
    @PostMapping("/add")
    @Transactional
    public R addSong(SongDTO songDTO, @RequestParam MultipartFile file) {
        log.info("添加音乐：{}",songDTO);
        log.info("音乐源：{}",file);
        String filePath;
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName= PathConstant.SONG_URL + UUID.randomUUID().toString()+suffix;
            filePath = aliOssUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return R.fatal(MessageConstant.UPLOAD_FAILED +e.getMessage());
        }
        Song song=new Song();
        BeanUtils.copyProperties(songDTO,song);
        song.setUrl(filePath);
        song.setPic(PathConstant.SONG_DEFAULT_PIC);
        songService.addSong(song);
        return R.success("添加成功");
    }

    // 删除歌曲
    @DeleteMapping("/delete")
    public R deleteSong(@RequestParam Integer id) {
        log.info("删除歌曲{}",id);
        songService.deleteSong(id);
        return R.success("删除成功");
    }

    // 返回所有歌曲
    @GetMapping
    public R allSong() {
        log.info("返回所有歌曲");
        List<Song> songs=songService.allSong();
        return R.success(null,songs);
    }

    //TODO ok
    // 返回指定歌曲ID的歌曲
    @GetMapping("/detail")
    public R songOfId(@RequestParam Integer id) {
        log.info("返回ID{}的歌曲",id);
        List<Song> songs=songService.songOfId(id);
        return R.success(null,songs);
    }

    // 返回指定歌手ID的歌曲
    @GetMapping("/singer/detail")
    public R songOfSingerId(@RequestParam Integer singerId) {
        log.info("返回歌手ID{}的歌曲",singerId);
        List<Song> songs=songService.songOfSingerId(singerId);
        return R.success(null,songs);
    }

    // 返回指定歌手名的歌曲
    @GetMapping("/singerName/detail")
    public R songOfSingerName(@RequestParam String name) {
        log.info("返回歌手姓名{}的歌曲",name);
        List<Song> songs=songService.songOfSingerName(name);
        return R.success(null,songs);
    }

    // 更新歌曲信息
    @PostMapping("/update")
    public R updateSongMsg(@RequestBody SongDTO songDTO) {
        log.info("更新歌曲信息:{}",songDTO);
        songService.updateSongMsg(songDTO);
        return R.success("更新成功");
    }

    // 更新歌曲图片
    @PostMapping("/img/update")
    @Transactional
    public R updateSongPic(@RequestParam MultipartFile file, @RequestParam Integer id) {
        log.info("更新音乐{}的图片：{}",id,file);
        String filePath;
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName= PathConstant.SONG_PIC + UUID.randomUUID().toString()+suffix;
            filePath = aliOssUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return R.fatal(MessageConstant.UPLOAD_FAILED +e.getMessage());
        }
        Song song=Song.builder().id(id).pic(filePath).build();
        songService.updateSongPic(song);
        return R.success("更新成功");
    }

    // 更新歌曲
    @PostMapping("/url/update")
    @Transactional
    public R updateSongUrl(@RequestParam MultipartFile file, @RequestParam Integer id) {
        log.info("更新音乐{}的源：{}",id,file);
        String filePath;
        try {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName= PathConstant.SONG_URL + UUID.randomUUID().toString()+suffix;
            filePath = aliOssUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return R.fatal(MessageConstant.UPLOAD_FAILED +e.getMessage());
        }
        Song song=Song.builder().id(id).url(filePath).build();
        songService.updateSongUrl(song);
        return R.success("更新成功");
    }
}
