package com.dream.room.bill.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@RestController
public class IndexController {

    @Resource
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String ok(){
        return "Server run okay!";
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
