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
    @ApiOperation(value = "删除账单")
    public void deleteById(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        billService.deleteById(id);
    }

    @GetMapping("{no}/detail")
    @ApiOperation(value = "查询账单明细")
    public List<BillDetail> findDetails(@PathVariable String no) {
        return billService.findDetails(no);
    }

    @PostMapping("{no}/detail")
    @ApiOperation(value = "保存账单明细")
    public BillDetail saveDetails(HttpServletResponse response, @PathVariable String no, @RequestBody BillDetail detail) {
        response.setStatus(HttpStatus.CREATED.value());
        return billService.saveDetail(no, detail);
    }

    @PutMapping("{no}/detail/{detailId}")
    @ApiOperation(value = "修改账单明细")
    public BillDetail updateDetail(@PathVariable String no, @PathVariable Long detailId,
                                         @RequestBody BillDetail detail) {
        detail.setId(detailId);
        return billService.saveDetail(no, detail);
    }

    @DeleteMapping("{no}/detail/{detailId}")
    @ApiOperation(value = "删除账单明细")
    public void deleteDetail(HttpServletResponse response, @PathVariable String no, @PathVariable Long detailId) {
        response.setStatus(HttpStatus.NO_CONTENT.value());
        billService.deleteDetail(no, detailId);
    }

    @PutMapping("{id}/confirm")
    @ApiOperation(value = "确认账单", notes = "账单编辑完成")
    public void confirm(@PathVariable Long id){
        billService.confirm(id);
    }

    @PutMapping("{id}/complete")
    @ApiOperation(value = "完成账单", notes = "状态置为完成（所有待发货的明细都将变更为发货状态）")
    public void complete(@PathVariable Long id){
        billService.complete(id);
    }

    @PutMapping("{id}/cancel")
    @ApiOperation(value = "取消账单", notes = "状态置为取消（已发货除外）")
    public void cancel(@PathVariable Long id){
        billService.cancel(id);
    }

    @PutMapping("{no}/detail/{detailId}/complete")
    @ApiOperation(value = "账单明细发货", notes = "将某一明细状态置为发货")
    public void completeDetail(@PathVariable String no, @PathVariable Long detailId) {
        billService.completeDetail(no, detailId);
    }

    @PutMapping("{no}/detail/{detailId}/cancel")
    @ApiOperation(value = "账单明细取消发货", notes = "将某一明细状态置为取消")
    public void cancelDetail(@PathVariable String no, @PathVariable Long detailId) {
        billService.cancelDetail(no, detailId);
    }

}
