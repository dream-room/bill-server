package com.dream.room.bill.controller;

import com.dream.room.bill.service.BillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/5/23.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Resource
    private BillService billService;

    @GetMapping("/bill/handling")
    @ApiOperation(value = "查询正在进行中的订单数量")
    public long countBillHandling(){
        return billService.countBillHandling();
    }

    @GetMapping("/bill/delivery")
    @ApiOperation(value = "查询需要交付的订单数量")
    public long countBillDelivery(){
        return billService.countBillDelivery();
    }

    @GetMapping("/bill/done/monthly")
    @ApiOperation(value = "查询当月完成的订单数量")
    public long countBillMonthlyDone(){
        return billService.countBillMonthlyDone();
    }

}
