package com.dream.room.bill.repository;

import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
public interface BillRepository extends MyCrudRepository<Bill> {

    @Modifying
    @Query("update Bill set status = :status where id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") int status);

    Bill findByNo(String no);

    @Modifying
    @Query("update Bill set status = 2, confirmTime = :now where id = :id and status = 1")
    int confirmStatus(@Param("id") Long id, @Param("now") Instant now);

    @Modifying
    @Query("update Bill set status = 3, doneTime = :now where id = :id and status = 2")
    int completeStatus(@Param("id") Long id, @Param("now") Instant now);
}
