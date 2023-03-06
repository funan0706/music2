package com.example.music2;

import com.example.music2.entity.User;
import com.example.music2.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Music2ApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        List<User> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
