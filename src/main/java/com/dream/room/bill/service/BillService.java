package com.dream.room.bill.service;

import com.dream.room.bill.common.BillException;
import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.common.utils.RandomStringUtils;
import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.entity.GoodsArchive;
import com.dream.room.bill.repository.BillDetailRepository;
import com.dream.room.bill.repository.BillRepository;
import com.dream.room.bill.repository.GoodsArchiveRepository;
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
import java.time.Instant;
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
    private GoodsArchiveRepository goodsArchiveRepository;

    /**
     * 保存账单
     */
    @Override
    public Bill save(Bill entity) {
        if (StringUtils.isEmpty(entity.getNo())){
            entity.setNo(RandomStringUtils.getNextVal());
        }
        entity.setStatus(1);
        return super.save(entity);
    }

    /**
     * 查询账单
     */
    public Page<Bill> findAll(PageQueryDto dto) {
        Bill bill = new Bill();
        BeanUtils.copyProperties(dto,bill);
        return findAll(Example.of(bill),dto);
    }

    /**
     * 删除账单
     * limit status=1,details.size()=0
     */
    @Override
    public void deleteById(Long id) {
        billRepository.findById(id).ifPresent(item -> {
            if (item.getStatus() != 1){
                throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可删除！");
            }
            final int i = billDetailRepository.countByBillNo(item.getNo());
            if (i != 0){
                throw BillException.ofBadRequest("订单状态异常", "当前订单包含子订单，请删除后再次删除！");
            }
        });
        super.deleteById(id);
    }

    /**
     * 查询账单明细
     */
    public List<BillDetail> findDetails(String no) {
        return billDetailRepository.findAllByBillNo(no);
    }

    /**
     * 保存账单明细
     */
    public BillDetail saveDetail(String no, BillDetail detail) {
        Bill bill = billRepository.findByNo(no);
        if (bill.getStatus() != 1){
            throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可编辑！");
        }
        detail.setBillNo(no);
        if (detail.getPriority() == null) {
            detail.setPriority(5);
        }

        //物品信息
        GoodsArchive archive = goodsArchiveRepository.findFirstByGoodsIdOrderByIdDesc(detail.getGoodsId())
                .orElseThrow(() -> BillException.ofNotFound("未找到", "该物品未建立档案，请确认编辑完成并生成档案后再次添加"));
        detail.setStatus(1);
        detail.setGoodsId(archive.getGoodsId());
        detail.setGoodsVersion(archive.getVersion());
        detail.setGoodsName(archive.getName());
        if (detail.getUnitPrice() == null) {
            detail.setUnitPrice(archive.getPrice());
        }
        detail.setTotal(detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getAmount())));

        return billDetailRepository.save(detail);
    }

    /**
     * 删除账单明细
     */
    public void deleteDetail(String no, Long detailId) {
        Bill bill = billRepository.findByNo(no);
        if (bill.getStatus() != 1){
            throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可编辑！");
        }
        billDetailRepository.deleteById(detailId);
    }

    /**
     * 确认账单，编辑完成
     */
    @Transactional
    public void confirm(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> BillException.ofNotFound("订单未找到","请确定该订单是否存在!"));
        billRepository.confirmStatus(id, Instant.now());
        billDetailRepository.confirmByBill(bill.getNo());
    }

    /**
     * 完成账单，状态置为完成（所有待发货的明细都将变更为发货状态）
     */
    @Transactional
    public void complete(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> BillException.ofNotFound("订单未找到","请确定该订单是否存在!"));
        billRepository.completeStatus(id, Instant.now());
        billDetailRepository.completeByBill(bill.getNo(), Instant.now());
    }

    /**
     * 取消账单，状态置为取消（已发货除外）
     */
    @Transactional
    public void cancel(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> BillException.ofNotFound("订单未找到","请确定该订单是否存在!"));
        billRepository.updateStatus(id,4);
        billDetailRepository.cancelByBill(bill.getNo());
    }

    public void completeDetail(String no, Long detailId) {
        Bill bill = billRepository.findByNo(no);
        if (bill.getStatus() != 2){
            throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可发货！");
        }
        billDetailRepository.complete(detailId, Instant.now());
    }

    public void cancelDetail(String no, Long detailId) {
        Bill bill = billRepository.findByNo(no);
        if (bill.getStatus() != 2){
            throw BillException.ofBadRequest("订单状态异常", "当前订单状态不可取消发货！");
        }
        billDetailRepository.updateStatus(detailId, 4);
    }
}
