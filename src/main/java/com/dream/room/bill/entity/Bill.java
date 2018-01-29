package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Bill extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createTime;
    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updateTime;

    //编号
    @Column(unique = true, nullable = false, length = 64)
    private String no;
    //订单名称
    @Column(nullable = false)
    private String name;

}
