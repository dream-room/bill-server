package com.dream.room.bill.repository;

import com.dream.room.bill.entity.GoodsArchive;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/4/16.
 */
public interface GoodsArchiveRepository extends MyCrudRepository<GoodsArchive> {

    Optional<GoodsArchive> findFirstByGoodsIdOrderByIdDesc(Long goodsId);

    Optional<GoodsArchive> findByGoodsIdAndVersion(Long goodsId, Integer version);

    List<GoodsArchive> findAllByGoodsIdOrderByIdDesc(Long goodsId);

    @Query("select a.version from GoodsArchive as a where a.goodsId = :goodsId")
    List<Integer> findVersionsByGoodsId(@Param("goodsId") Long goodsId);

    void deleteByGoodsIdAndVersion(Long goodsId, Integer version);

}
