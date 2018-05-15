package com.dream.room.bill.controller;

import com.dream.room.bill.common.BillException;
import com.dream.room.bill.common.jwt.JwtAuthService;
import com.dream.room.bill.common.model.ErrorResult;
import com.dream.room.bill.entity.User;
import com.dream.room.bill.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@RestController
public class IndexController {

    @Resource
    private JwtAuthService jwtAuthService;

    @Resource
    private UserService userService;

    @GetMapping("/")
    public Map<String,String> ok(){
        Map<String,String> map = new HashMap<>();
        map.put("info","Server run okay!");
        map.put("auth-token","/auth/token");
        map.put("doc-url","/swagger-ui.html");
        return map;
    }

    @PostMapping("/auth/token")
    @ApiOperation(value = "获取token接口")
    public String authToken(@RequestBody User user){
        boolean flag = userService.login(user);
        if (flag) {
            return jwtAuthService.createStringToken("admin");
        }
        throw BillException.of(HttpStatus.BAD_REQUEST, "获取token失败", "请确保用户名密码正确！");
    }

    @GetMapping("/auth/fail")
    @ApiOperation(value = "Token验证失败")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResult authFail(){
        return ErrorResult.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .title("Token验证失败")
                .message("可能Token过期，请重新登录，获取Token！")
                .build();
    }

}
