package com.dream.room.bill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String ok(){
        return "Server run okay!";
    }

}
