package com.dream.room.bill.service;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.Component;
import com.dream.room.bill.repository.ComponentRepository;
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
public class ComponentService extends BaseCrudService<Component,Long,ComponentRepository> {

    @Resource
    private ComponentRepository componentRepository;

    public Page<Component> findAll(PageQueryDto dto) {
        Component component = new Component();
        BeanUtils.copyProperties(dto,component);
        return findAll(Example.of(component),dto);
    }

}
