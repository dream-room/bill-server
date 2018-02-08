package com.dream.room.bill.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String login(String no,String password){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(no,password);
        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return "success";
        }
        return "fail";
    }

}
