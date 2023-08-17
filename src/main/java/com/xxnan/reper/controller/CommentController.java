package com.xxnan.reper.controller;


import com.xxnan.reper.common.result.R;
import com.xxnan.reper.pojo.DTO.CommentDTO;
import com.xxnan.reper.pojo.entity.Comment;
import com.xxnan.reper.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/comment")
@RestController
@Api(tags = "评论相关接口")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    @ApiOperation("新增评论")
    public R addComment(@RequestBody CommentDTO commentDTO) {
        log.info("提交评论：{}",commentDTO);
        commentService.addComment(commentDTO);
        return R.success("评论成功");
    }

    /**
     * 在删除评论的同时删除了评论相关的点赞记录
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public R deleteComment(@RequestParam Integer id) {
        log.info("删除ID{}的评论",id);
        commentService.deleteComment(id);
        return R.success("删除成功");
    }

    @GetMapping("/song/detail")
    public R commentOfSongId(@RequestParam Integer songId) {
        log.info("查询音乐{}的评论",songId);
        List<Comment>comments = commentService.commentOfSongId(songId);
        return R.success(null,comments);
    }

    @GetMapping("/songList/detail")
    public R commentOfSongListId(@RequestParam Integer songListId) {
        log.info("查询歌单{}的评论",songListId);
        List<Comment>comments = commentService.commentOfSongListId(songListId);
        return R.success(null,comments);
    }

    @PostMapping("/like")
    public R commentOfLike(@RequestBody CommentDTO commentDTO) {
        log.info("修改评论显示的点赞数{}",commentDTO.getUp());
        commentService.updateCommentMsg(commentDTO);
        return R.success("点赞成功");
    }
}
