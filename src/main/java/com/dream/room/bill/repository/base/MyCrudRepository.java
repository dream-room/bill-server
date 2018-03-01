package com.dream.room.bill.repository.base;

import com.dream.room.bill.common.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/28.
 */
@NoRepositoryBean
public interface MyCrudRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
