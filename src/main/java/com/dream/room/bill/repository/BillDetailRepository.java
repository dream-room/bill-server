package com.dream.room.bill.repository;

import com.dream.room.bill.entity.BillDetail;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
public interface BillDetailRepository extends MyCrudRepository<BillDetail> {

    List<BillDetail> findAllByBillNo(String billNo);

    int countByBillNo(String billNo);

    @Modifying
    @Query("update BillDetail set status = 2 where billNo = :billNo and status = 1")
    int confirmByBill(@Param("billNo") String billNo);

    @Modifying
    @Query("update BillDetail set status = 3, doneTime = :now where billNo = :billNo and status = 2")
    int completeByBill(@Param("billNo") String billNo, @Param("now") Instant now);

    @Modifying
    @Query("update BillDetail set status = 4 where billNo = :billNo and status = 2")
    int cancelByBill(@Param("billNo") String billNo);

    @Modifying
    @Query("update BillDetail set status = :status where id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") int status);

    @Modifying
    @Query("update BillDetail set status = 3, doneTime = :now where id = :id")
    int complete(@Param("id") Long id, @Param("now") Instant now);
}
