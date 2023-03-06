package com.example.music2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.music2.common.Result;
import com.example.music2.dto.PageInfo;
import com.example.music2.entity.Music;
import com.example.music2.entity.User;
import com.example.music2.service.MusicService;
import com.example.music2.serviceImpl.MusicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.music2.controller.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-03-06
 */
@RestController
@RequestMapping("/music")
public class MusicController extends BaseController {

    @Autowired
    private MusicServiceImpl musicService;

    @PostMapping("/list")
    public Result<?> list(){
        return Result.success(musicService.list());
    }

    @PostMapping("getById")
    public Result<?> getById(@RequestBody Music music){
        Music music1=musicService.getById(music.getId());
        return Result.success(music1);
    }

    // 通过名字来查询
    // 最好用模糊查询
    @PostMapping("getByName")
    public Result<?> getByName(@RequestBody Music music){
        QueryWrapper<Music> wrapper=new QueryWrapper<>();
        wrapper.like("title",music.getTitle());
        List<Music> music1=musicService.list(wrapper);
        if(music1 != null){
            return Result.success(music1);
        }else {
            return Result.error("没有这首歌");
        }
    }

    // 使用mp带的分页查询
    @PostMapping("/page")
    public Result<?> page(@RequestBody PageInfo pageInfo){
        Page<Music> page=musicService.page(
                new Page<>(
                        pageInfo.getPageNum(),
                        pageInfo.getPageSize()
                )
        );
        return Result.success(page);
    }

}
