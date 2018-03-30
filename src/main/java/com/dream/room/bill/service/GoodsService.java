package com.dream.room.bill.service;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Goods;
import com.dream.room.bill.entity.GoodsComponent;
import com.dream.room.bill.repository.ComponentRepository;
import com.dream.room.bill.repository.GoodsComponentRepository;
import com.dream.room.bill.repository.GoodsRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Service
public class GoodsService extends BaseCrudService<Goods,GoodsRepository> {

    @Resource
    private GoodsRepository goodsRepository;
    @Resource
    private GoodsComponentRepository goodsComponentRepository;
    @Resource
    private ComponentRepository componentRepository;

    public Page<Goods> findAll(PageQueryDto dto) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(dto,goods);
        return findAll(Example.of(goods),dto);
    }

    public List<GoodsComponent> findComponentsById(Long id) {
        return goodsComponentRepository.findAllByGoodsId(id);
    }

    @Override
    @Transactional
    public Goods save(Goods goods) {
        //保存物品
        goods.setPrice(new BigDecimal(0));
        goods = goodsRepository.save(goods);
        return goods;

        //保存零件
        /*final Goods finalGoods = goods;
        List<Component> components = componentRepository.findAllById(componentsIds);
        BigDecimal price = components.stream()
                .map(Component::getPrice)
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
        goods.setPrice(price);
        goods = goodsRepository.save(goods);
        List<GoodsComponent> collect = components.stream()
                .map(item -> GoodsComponent.builder()
                        .goodsId(finalGoods.getId())
                        .componentId(item.getId())
                        .componentName(item.getName())
                        .num(1)
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());
        goodsComponentRepository.saveAll(collect);
        return goods;*/
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        goodsComponentRepository.deleteAllByGoodsId(id);
        super.deleteById(id);
    }
}
