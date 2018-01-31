package com.dream.room.bill.controller;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.User;
import com.dream.room.bill.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping
    public Page<User> findAll(PageQueryDto dto) {
        return userService.findAll(dto);
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public User save(HttpServletResponse response, @RequestBody User user){
        response.setStatus(HttpStatus.CREATED.value());
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        userService.deleteById(id);
    }

    @PutMapping("password")
    public int updatePassword(@RequestBody Map<String,String> map){
        return userService.updatePassword(map.get("no"),map.get("password"));
    }

}
