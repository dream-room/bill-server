package com.dream.room.bill.controller;

import com.dream.room.bill.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public Map<String,String> ok(){
        Map<String,String> map = new HashMap<>();
        map.put("info","Server run okay!");
        map.put("rest-login-url","/login/rest");
        map.put("doc-url","/swagger-ui.html");
        return map;
    }

    @PostMapping("/login/rest")
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

}
