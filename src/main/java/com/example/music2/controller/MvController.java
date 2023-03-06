package com.example.music2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music2.common.Result;
import com.example.music2.entity.Music;
import com.example.music2.serviceImpl.MvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/mv")
public class MvController extends BaseController {

    @Autowired
    private MvServiceImpl mvService;

    @GetMapping("list")
    public Result<?> list(){
        return Result.success(mvService.list());
    }
}
