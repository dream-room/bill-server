package com.dream.room.bill.service;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.entity.User;
import com.dream.room.bill.repository.BillRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@Service
public class BillService extends BaseCrudService<Bill,Long,BillRepository> {

    @Resource
    private BillRepository billRepository;

    public Page<Bill> findAll(PageQueryDto dto) {
        Bill bill = new Bill();
        BeanUtils.copyProperties(dto,bill);
        return findAll(Example.of(bill),dto);
    }

}
