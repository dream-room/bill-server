package com.dream.room.bill.service;

import com.dream.room.bill.entity.User;
import com.dream.room.bill.repository.UserRepository;
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
public class UserService {

    @Resource
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public int updatePassword( String no, String password){
        return userRepository.updatePassword(no,password);
    }

}
