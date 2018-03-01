package com.dream.room.bill.repository;

import com.dream.room.bill.entity.Bill;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/5.
 */
public interface BillRepository extends MyCrudRepository<Bill> {

    @Modifying
    @Query("update Bill set status = :status where id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") int status);

}
