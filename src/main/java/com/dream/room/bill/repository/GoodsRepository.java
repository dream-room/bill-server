package com.dream.room.bill.repository;

import com.dream.room.bill.entity.Goods;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
public interface GoodsRepository extends MyCrudRepository<Goods> {

    /**
     * 修改密码
     */
    @Modifying
    @Query("update Goods g set g.price = :price where g.id = :goodsId")
    int updateGoodsPrice(@Param("goodsId") Long goodsId, @Param("price") BigDecimal price);

}
