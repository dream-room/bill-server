package com.dream.room.bill.controller;

import com.dream.room.bill.entity.User;
import com.dream.room.bill.service.UserService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("password")
    public int updatePassword(@RequestBody Map<String,String> map){
        return userService.updatePassword(map.get("no"),map.get("password"));
    }

}
