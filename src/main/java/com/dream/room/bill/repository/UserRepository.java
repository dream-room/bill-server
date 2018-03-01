package com.dream.room.bill.repository;

import com.dream.room.bill.entity.User;
import com.dream.room.bill.repository.base.MyCrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/8.
 */
public interface UserRepository extends MyCrudRepository<User> {

    /**
     * 修改密码
     */
    @Modifying
    @Query("update User u set u.password = :password where u.no = :no")
    int updatePassword(@Param("no") String no,@Param("password") String password);

    User findByNo(String no);

}
