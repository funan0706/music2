package com.example.music2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.music2.common.Result;
import com.example.music2.entity.Mlist;
import com.example.music2.entity.MusicList;
import com.example.music2.serviceImpl.MlistServiceImpl;
import com.example.music2.serviceImpl.MusicListServiceImpl;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.music2.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-03-06
 */
@RestController
@RequestMapping("/mlist")
public class MlistController extends BaseController {

    @Autowired
    private MlistServiceImpl mlistService;
    @Autowired
    private MusicListServiceImpl musicListService;

    @PostMapping("list")
    public Result<?> list(@RequestBody Integer id){
        QueryWrapper<Mlist> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uid",id);
        return Result.success(mlistService.list(queryWrapper));
    }

    @PostMapping("getById")
    public Result<?> getById(@RequestBody Integer id){

        return Result.success(mlistService.getById(id));
    }

    @PostMapping("deleteMlist")
    public Result<?> deleteMlist(@RequestBody Integer id){
        return Result.success(mlistService.removeById(id));
    }

    @PostMapping("deleteMusic")
    public Result<?> deleteMusic(@RequestBody MusicList musicList){
        // 删除歌曲后，歌单对应的sum也要--
        Mlist mlist = mlistService.getById(musicList.getLid());
        UpdateWrapper<Mlist> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("id",musicList.getLid()).set("sum",mlist.getSum()-1);
        mlistService.update(mlist,wrapper1);
        return Result.success(musicListService.removeById(musicList.getId()));
    }

    @PostMapping("saveMlist")
    public Result<?> saveMlist(@RequestBody Mlist mlist){
        // name,uid,
        return Result.success(mlistService.save(mlist));
    }

    // 需要uid，lid
    @PostMapping("saveMusic")
    public Result<?> saveMusic(@RequestBody MusicList musicList){
        // 歌单中已经插入该歌曲
        QueryWrapper<MusicList> wrapper=new QueryWrapper<>();
        wrapper.eq("mid",musicList.getMid()).eq("lid",musicList.getLid());
        MusicList musicList1=musicListService.getOne(wrapper);
        if (musicList1 != null){
            return Result.error("歌单中已经包含该歌曲！");
        }else {
            // 先获得对应的mlist
            Mlist mlist = mlistService.getById(musicList.getLid());
            UpdateWrapper<Mlist> wrapper1 = new UpdateWrapper<>();
            wrapper1.eq("id",musicList.getLid()).set("sum",mlist.getSum()+1);
            mlistService.update(mlist,wrapper1);
            // 还要实现sum++,从musiclist中的lid中获得mlist，再在对应的mlist中的sum++
            musicListService.save(musicList);
            return Result.success();
        }
    }


}
