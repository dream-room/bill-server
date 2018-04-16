package com.dream.room.bill.service;

import com.dream.room.bill.common.BillException;
import com.dream.room.bill.entity.Goods;
import com.dream.room.bill.entity.GoodsArchive;
import com.dream.room.bill.entity.GoodsComponent;
import com.dream.room.bill.repository.GoodsArchiveRepository;
import com.dream.room.bill.repository.GoodsComponentRepository;
import com.dream.room.bill.repository.GoodsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/4/16.
 */
@Service
public class GoodsArchiveService {

    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private GoodsComponentRepository goodsComponentRepository;
    @Resource
    private GoodsArchiveRepository goodsArchiveRepository;

    /**
     * 生成物品档案
     */
    public GoodsArchive generate(Long goodsId) {
        final Goods goods = goodsRepository.findById(goodsId)
                .orElseThrow(() -> BillException.ofNotFound("未找到","该物品不存在！"));
        final GoodsArchive preHistory = goodsArchiveRepository.findFirstByGoodsIdOrderByIdDesc(goodsId).orElse(null);
        int version = 1;
        if (preHistory != null) {
            version = preHistory.getVersion() + 1;
        }
        GoodsArchive history = new GoodsArchive();
        history.setGoodsId(goods.getId());
        history.setName(goods.getName());
        history.setPrice(goods.getPrice());
        history.setVersion(version);

        //拼接明细
        final List<GoodsComponent> components = goodsComponentRepository.findAllByGoodsId(goodsId);
        List<String> items = components.stream()
                .map(item -> item.getComponentName() + "(" + item.getPrice() + ")" + "x" + item.getNum())
                .collect(Collectors.toList());
        history.setGoodsDetail(String.join("|",items));
        return goodsArchiveRepository.save(history);
    }

    /**
     * 查询物品档案
     */
    public List<GoodsArchive> findDetails(Long goodsId) {
        return goodsArchiveRepository.findAllByGoodsIdOrderByIdDesc(goodsId);
    }

    /**
     * 查询物品档案
     */
    public GoodsArchive findDetail(Long goodsId, Integer goodsVersion) {
        return goodsArchiveRepository.findByGoodsIdAndVersion(goodsId, goodsVersion).orElse(null);
    }

    /**
     * 回退物品档案
     */
    public GoodsArchive returnVersion(Long goodsId, Integer goodsVersion) {
        final GoodsArchive preHistory = goodsArchiveRepository.findFirstByGoodsIdOrderByIdDesc(goodsId)
                .orElseThrow(() -> BillException.ofNotFound("未找到","该物品不存在历史档案！"));
        final GoodsArchive returnHistory = goodsArchiveRepository.findByGoodsIdAndVersion(goodsId, goodsVersion)
                .orElseThrow(() -> BillException.ofNotFound("未找到","需回退的历史档案不存在！"));
        GoodsArchive history = new GoodsArchive();
        BeanUtils.copyProperties(returnHistory,history);
        history.setVersion(preHistory.getVersion() + 1);
        history.setId(null);
        return goodsArchiveRepository.save(history);
    }
}
