package com.dream.room.bill.service;

import com.dream.room.bill.common.BillException;
import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.common.utils.RandomStringUtils;
import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.repository.BillDetailRepository;
import com.dream.room.bill.repository.BillRepository;
import com.dream.room.bill.repository.GoodsComponentRepository;
import com.dream.room.bill.repository.GoodsRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
@Service
public class BillService extends BaseCrudService<Bill,BillRepository> {

    @Resource
    private BillRepository billRepository;
    @Resource
    private BillDetailRepository billDetailRepository;
    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private GoodsComponentRepository goodsComponentRepository;

    @Override
    public Bill save(Bill entity) {
        if (StringUtils.isEmpty(entity.getNo())){
            entity.setNo(RandomStringUtils.getNextVal());
        }
        entity.setStatus(1);
        return super.save(entity);
    }

    public Page<Bill> findAll(PageQueryDto dto) {
        Bill bill = new Bill();
        BeanUtils.copyProperties(dto,bill);
        return findAll(Example.of(bill),dto);
    }

    @Override
    public void deleteById(Long id) {
        billRepository.findById(id).ifPresent(item -> {
            if (item.getStatus() != 1){
                throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可编辑！");
            }
            final int i = billDetailRepository.countByBillNo(item.getNo());
            if (i != 0){
                throw BillException.ofBadRequest("订单状态异常", "当前订单包含子订单，请删除后再次删除！");
            }
        });
        super.deleteById(id);
    }

    public List<BillDetail> findDetails(String no) {
        return billDetailRepository.findAllByBillNo(no);
    }

    public BillDetail saveDetail(String no, BillDetail detail) {
        Bill bill = billRepository.findByNo(no);
        if (bill.getStatus() != 1){
            throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可编辑！");
        }
        detail.setStatus(1);
        //货物名称
        goodsRepository.findById(detail.getGoodsId()).ifPresent(item -> {
            detail.setGoodsId(detail.getGoodsId());
            detail.setGoodsVersion(1);
            detail.setGoodsName(item.getName());
            detail.setUnitPrice(item.getPrice());
            detail.setTotal(item.getPrice().multiply(BigDecimal.valueOf(detail.getAmount())));
        });

        return billDetailRepository.save(detail);
    }

    public void deleteDetail(String no, Long detailId) {
        Bill bill = billRepository.findByNo(no);
        if (bill.getStatus() != 1){
            throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可编辑！");
        }
        billDetailRepository.deleteById(detailId);
    }

    @Transactional
    public int updateStatus(Long id, int i) {
        return billRepository.updateStatus(id,i);
    }

    @Transactional
    public void cancel(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> BillException.ofNotFound("订单未找到","请确定该订单是否存在!"));
        billRepository.updateStatus(id,4);
        billDetailRepository.cancelByBill(bill.getNo());
    }
}
