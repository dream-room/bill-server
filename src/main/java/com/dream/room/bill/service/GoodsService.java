package com.dream.room.bill.service;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Goods;
import com.dream.room.bill.repository.GoodsRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Service
public class GoodsService extends BaseCrudService<Goods,Long,GoodsRepository> {

    @Resource
    private GoodsRepository goodsRepository;

    public Page<Goods> findAll(PageQueryDto dto) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(dto,goods);
        return findAll(Example.of(goods),dto);
    }

}