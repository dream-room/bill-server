package com.dream.room.bill.service;

import com.dream.room.bill.common.PageQueryDto;
import com.dream.room.bill.entity.User;
import com.dream.room.bill.repository.UserRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Service
public class UserService extends BaseCrudService<User,UserRepository> {

    @Resource
    private UserRepository userRepository;

    public Page<User> findAll(PageQueryDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return findAll(Example.of(user),dto);
    }

    @Transactional
    public int updatePassword( String no, String password){
        return userRepository.updatePassword(no,password);
    }

}
