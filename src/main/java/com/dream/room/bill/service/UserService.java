package com.dream.room.bill.service;

import com.dream.room.bill.entity.User;
import com.dream.room.bill.repository.UserRepository;
import com.dream.room.bill.service.base.BaseCrudService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Service
public class UserService extends BaseCrudService<User,Long,UserRepository> {

    @Resource
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Example<User> example, Pageable pageable) {
        return null;
    }

    @Transactional
    public int updatePassword( String no, String password){
        return userRepository.updatePassword(no,password);
    }

}
