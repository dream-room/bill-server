package com.dream.room.bill.controller;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.User;
import com.dream.room.bill.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    @ApiOperation(value = "查询所有用户")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping
    @ApiOperation(value = "查询用户信息", notes = "分页查询")
    public Page<User> findAll(PageQueryDto dto) {
        return userService.findAll(dto);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "查询单个用户信息")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "添加/修改用户")
    public User save(HttpServletResponse response, @RequestBody User user){
        response.setStatus(HttpStatus.CREATED.value());
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除用户")
    public void deleteById(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        userService.deleteById(id);
    }

    @PutMapping("password")
    @ApiOperation(value = "修改密码")
    public int updatePassword(@RequestBody Map<String,String> map){
        return userService.updatePassword(map.get("no"),map.get("password"));
    }

}
