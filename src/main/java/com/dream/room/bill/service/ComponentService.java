package com.dream.room.bill.service;

import com.dream.room.bill.common.model.ErrorResult;
import com.dream.room.bill.dto.ComponentDto;
import com.dream.room.bill.entity.Component;
import com.dream.room.bill.entity.Goods;
import com.dream.room.bill.entity.GoodsComponent;
import com.dream.room.bill.repository.ComponentRepository;
import com.dream.room.bill.repository.GoodsComponentRepository;
import com.dream.room.bill.repository.GoodsRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Service
public class ComponentService extends BaseCrudService<Component,ComponentRepository> {

    @Resource
    private ComponentRepository componentRepository;
    @Resource
    private GoodsComponentRepository goodsComponentRepository;
    @Resource
    private GoodsRepository goodsRepository;

    public Page<Component> findAll(ComponentDto dto) {
        Component component = new Component();
        BeanUtils.copyProperties(dto,component);
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        return findAll(Example.of(component,matcher),dto);
    }

    @Override
    public void deleteById(Long id) {
        List<GoodsComponent> goodsComponents = goodsComponentRepository.findAllByComponentId(id);
        if (!CollectionUtils.isEmpty(goodsComponents)){
            List<Long> ids = goodsComponents.stream()
                    .map(GoodsComponent::getGoodsId)
                    .distinct()
                    .collect(Collectors.toList());
            List<Goods> goods = goodsRepository.findAllById(ids);
            throw ErrorResult.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .title("资源占用！")
                    .message("部分物品占用该零件")
                    .object(goods)
                    .build().toException();
        }
        super.deleteById(id);
    }
}
