package com.example.music2.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.music2.common.Result;
import com.example.music2.dto.PageInfo;
import com.example.music2.entity.User;
import com.example.music2.mapper.UserMapper;
import com.example.music2.serviceImpl.UserServiceImpl;

import com.example.music2.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public Result<?> list(){
//        return userService.list();
        return Result.success(userService.list());
    }

    // 使用mp带的分页查询
    @PostMapping("/page")
    public Result<?> page(@RequestBody PageInfo pageInfo){
        Page<User> page=userService.page(
                new Page<>(
                        pageInfo.getPageNum(),
                        pageInfo.getPageSize()
                )
        );
        return Result.success(page);
    }

    // 添加
    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody User user){
        // 查看用户名是否重复
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User user1=userMapper.selectOne(queryWrapper);

        if(user1==null){
            userService.save(user);
            return Result.success(user);
        } else {
            return Result.error("用户名重复！");
        }
    }

    // 修改
    @PostMapping("/update")
    public Result<?> update(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }

    // 登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername())
                .eq(User::getPassword,user.getPassword());
        User user1=userService.getOne(wrapper);

        if(user1 != null){
//            Map<String,String> payload=new HashMap<>();
//            payload.put("username",user.getUsername());

            String token = JwtUtils.generateToken(user1);
            HashMap<Object,Object> map = new HashMap<>();
            map.put("user",user1);
            map.put("token",token);
            return Result.success(map);

        }else {
            return Result.error("用户名或密码错误");
        }

    }

    @PostMapping("getById")
    public Result<?> getById(@RequestBody User user){
        User user1=userService.getById(user.getId());
        return Result.success(user1);
    }

    // 删除
    // 用户这个操作没必要实现
//    @PostMapping("/delete")
//    public Result<?> delete(@RequestBody Integer id){
//        userService.removeById(id);
//        return Result.success();
//    }
}
