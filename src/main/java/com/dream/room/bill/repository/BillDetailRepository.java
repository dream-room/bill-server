package com.dream.room.bill.repository;

import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
public interface BillDetailRepository extends MyCrudRepository<BillDetail> {

    List<BillDetail> findAllByBillNo(String billNo);

    @Modifying
    @Query("update BillDetail set status = 4 where goodsId = :goodsId and status <> 3")
    int cancelByBill(@Param("goodsId") Long goodsId);
}
