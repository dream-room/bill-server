package com.dream.room.bill.controller;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.service.BillService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@RestController
@RequestMapping("bills")
public class BillController {

    @Resource
    private BillService billService;

    @GetMapping("{id}")
    public Bill findById(@PathVariable Long id){
        return billService.findById(id);
    }

    @GetMapping
    public Page<Bill> findAll(PageQueryDto dto) {
        return billService.findAll(dto);
    }
}
