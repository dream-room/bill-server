package com.dream.room.bill.controller;

import com.dream.room.bill.dto.ComponentDto;
import com.dream.room.bill.entity.Component;
import com.dream.room.bill.service.ComponentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@RestController
@RequestMapping("components")
public class ComponentController {

    @Resource
    private ComponentService componentService;

    @GetMapping
    @ApiOperation(value = "查询零件信息", notes = "分页查询")
    public Page<Component> findAll(ComponentDto dto) {
        return componentService.findAll(dto);
    }

    @GetMapping("all")
    @ApiOperation(value = "查询全部零件")
    public List<Component> findAll() {
        return componentService.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "查询单个零件信息")
    public Component findById(@PathVariable Long id){
        return componentService.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "添加/修改零件")
    public Component save(HttpServletResponse response, @RequestBody Component component){
        response.setStatus(HttpStatus.CREATED.value());
        return componentService.save(component);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "修改零件")
    public Component update(@PathVariable Long id, @RequestBody Component component){
        component.setId(id);
        return componentService.save(component);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除零件")
    public void deleteById(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        componentService.deleteById(id);
    }

}
