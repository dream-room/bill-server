package com.dream.room.bill.controller;

import com.dream.room.bill.entity.GoodsArchive;
import com.dream.room.bill.service.GoodsArchiveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @ResponseStatus(HttpStatus.CREATED)
    public GoodsArchive generate(@PathVariable Long goodsId){
        return goodsArchiveService.generate(goodsId);
    }

    @GetMapping("{goodsId}")
    @ApiOperation(value = "查询物品档案列表")
    public List<GoodsArchive> findDetails(@PathVariable Long goodsId){
        return goodsArchiveService.findDetails(goodsId);
    }

    @GetMapping("{goodsId}/versions")
    @ApiOperation(value = "查询物品档案versions列表")
    public List<Integer> findVersions(@PathVariable Long goodsId){
        return goodsArchiveService.findVersions(goodsId);
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

    @DeleteMapping("{goodsId}/{goodsVersion}")
    @ApiOperation(value = "删除物品档案", notes = "删除操作具有一定的危险性，已知会导致相关账单明细无法查询到物品档案")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArchive(@PathVariable Long goodsId, @PathVariable Integer goodsVersion){
        goodsArchiveService.deleteArchive(goodsId, goodsVersion);
    }

}
