package com.dream.room.bill.controller;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.service.BillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @ApiOperation(value = "查询单个账单信息")
    public Bill findById(@PathVariable Long id){
        return billService.findById(id);
    }

    @GetMapping
    @ApiOperation(value = "查询账单信息", notes = "分页查询")
    public Page<Bill> findAll(PageQueryDto dto) {
        return billService.findAll(dto);
    }

    @PostMapping
    @ApiOperation(value = "添加/修改账单")
    public Bill save(HttpServletResponse response, @RequestBody Bill bill){
        response.setStatus(HttpStatus.CREATED.value());
        return billService.save(bill);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "修改账单")
    public Bill update(@PathVariable Long id, @RequestBody Bill bill){
        bill.setId(id);
        return billService.save(bill);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "取消账单")
    public void cancel(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        billService.cancel(id);
    }

    @GetMapping("{no}/detail")
    @ApiOperation(value = "查询账单明细")
    public List<BillDetail> findDetails(@PathVariable String no) {
        return billService.findDetails(no);
    }

    @PostMapping("{no}/detail")
    @ApiOperation(value = "保存账单明细")
    public List<BillDetail> saveDetails(@PathVariable String no, @RequestBody BillDetail detail) {
        return billService.findDetails(no);
    }

    @PutMapping("{no}/detail/{detailId}")
    @ApiOperation(value = "修改账单明细")
    public List<BillDetail> updateDetail(@PathVariable String no, @PathVariable Long detailId,
                                         @RequestBody BillDetail detail) {
        detail.setId(detailId);
        return billService.findDetails(no);
    }

}
