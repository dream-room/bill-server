package com.dream.room.bill.controller;

import com.dream.room.bill.common.JwtAuthService;
import com.dream.room.bill.common.model.ErrorResult;
import com.dream.room.bill.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@RestController
public class IndexController {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtAuthService jwtAuthService;

    @GetMapping("/")
    public Map<String,String> ok(){
        Map<String,String> map = new HashMap<>();
        map.put("info","Server run okay!");
        map.put("auth-token","/auth/token");
        map.put("rest-principal","/login/principal");
        map.put("doc-url","/swagger-ui.html");
        return map;
    }

    @PostMapping("/login/rest")
    @ApiOperation(value = "Rest登录接口")
    public String login(@RequestBody User user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getNo(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return "success";
        }
        return "fail";
    }

    @PostMapping("/login/principal")
    @ApiOperation(value = "Rest获取登录用户接口")
    public Map<String,Object> check(){
        Map<String,Object> map = new HashMap<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(principal)){
            map.put("authentication","false");
            map.put("principal","anonymousUser");
            return map;
        }
        map.put("authentication","true");
        map.put("principal", principal);
        return map;
    }

    @GetMapping("/auth/fail")
    @ApiOperation(value = "Token验证失败")
    public ErrorResult authFail(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return ErrorResult.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .title("Token验证失败")
                .message("可能Token过期，请重新登录，获取Token！")
                .build();
    }

    @PostMapping("/auth/token")
    @ApiOperation(value = "获取token接口")
    public String authToken(@RequestBody User user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getNo(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return jwtAuthService.createStringToken("admin");
        }
        throw ErrorResult.builder()
                .status(HttpStatus.BAD_REQUEST)
                .title("获取token失败")
                .message("请确保用户名密码正确！")
                .build().toException();
    }

}
