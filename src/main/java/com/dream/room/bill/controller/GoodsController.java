package com.dream.room.bill.controller;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Goods;
import com.dream.room.bill.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping
    @ApiOperation(value = "查询物品信息", notes = "分页查询")
    public Page<Goods> findAll(PageQueryDto dto) {
        return goodsService.findAll(dto);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "查询单个物品信息")
    public Goods findById(@PathVariable Long id){
        return goodsService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "添加/修改物品")
    public Goods save(HttpServletResponse response, @RequestBody Goods goods){
        response.setStatus(HttpStatus.CREATED.value());
        return goodsService.save(goods);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除物品")
    public void deleteById(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        goodsService.deleteById(id);
    }

}
