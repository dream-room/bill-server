package com.dream.room.bill.repository;

import com.dream.room.bill.entity.GoodsArchive;
import com.dream.room.bill.repository.base.MyCrudRepository;

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
}
