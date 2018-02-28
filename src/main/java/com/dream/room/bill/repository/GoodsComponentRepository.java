package com.dream.room.bill.repository;

import com.dream.room.bill.entity.GoodsComponent;
import com.dream.room.bill.repository.base.MyCrudRepository;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
public interface GoodsComponentRepository extends MyCrudRepository<GoodsComponent,Long> {

    List<GoodsComponent> findAllByComponentId(Long componentId);
}
