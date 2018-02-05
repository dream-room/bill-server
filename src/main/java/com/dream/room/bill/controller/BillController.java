package com.dream.room.bill.controller;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.service.BillService;
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
    public Bill findById(@PathVariable Long id){
        return billService.findById(id);
    }

    @GetMapping
    public Page<Bill> findAll(PageQueryDto dto) {
        return billService.findAll(dto);
    }

    @GetMapping("{no}/detail")
    public List<BillDetail> findDetails(@PathVariable String no) {
        return billService.findDetails(no);
    }

    @PostMapping
    public Bill save(HttpServletResponse response, @RequestBody Bill bill){
        response.setStatus(HttpStatus.CREATED.value());
        return billService.save(bill);
    }

    @DeleteMapping("{id}")
    public void deleteById(HttpServletResponse response, @PathVariable Long id){
        response.setStatus(HttpStatus.NO_CONTENT.value());
        billService.updateStatus(id,2);
    }

}
