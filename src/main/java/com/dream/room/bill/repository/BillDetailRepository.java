package com.dream.room.bill.repository;

import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.repository.base.MyCrudRepository;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
public interface BillDetailRepository extends MyCrudRepository<BillDetail,Long> {

    List<BillDetail> findAllByBillNo(String billNo);

}
