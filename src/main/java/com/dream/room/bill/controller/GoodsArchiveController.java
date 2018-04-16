package com.dream.room.bill.controller;

import com.dream.room.bill.entity.GoodsArchive;
import com.dream.room.bill.service.GoodsArchiveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/4/16.
 */
@RestController
@RequestMapping("archive/goods")
public class GoodsArchiveController {

    @Resource
    private GoodsArchiveService goodsArchiveService;

    @PostMapping("{goodsId}/generate")
    @ApiOperation(value = "生成物品档案")
    public GoodsArchive generate(HttpServletResponse response, @PathVariable Long goodsId){
        response.setStatus(HttpStatus.CREATED.value());
        return goodsArchiveService.generate(goodsId);
    }

    @GetMapping("{goodsId}")
    @ApiOperation(value = "查询物品档案列表")
    public List<GoodsArchive> findDetails(@PathVariable Long goodsId){
        return goodsArchiveService.findDetails(goodsId);
    }

    @GetMapping("{goodsId}/{goodsVersion}")
    @ApiOperation(value = "查询物品档案")
    public GoodsArchive findDetail(@PathVariable Long goodsId, @PathVariable Integer goodsVersion){
        return goodsArchiveService.findDetail(goodsId, goodsVersion);
    }

    @PostMapping("{goodsId}/{goodsVersion}/return")
    @ApiOperation(value = "回退物品档案", notes = "回退到某一版本的物品档案")
    public GoodsArchive returnVersion(@PathVariable Long goodsId, @PathVariable Integer goodsVersion){
        return goodsArchiveService.returnVersion(goodsId, goodsVersion);
    }

}
