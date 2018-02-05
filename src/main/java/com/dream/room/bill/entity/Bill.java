package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"details"}, allowSetters = true)
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
    //订单状态 1：正常 2：作废
    @Column(nullable = false, columnDefinition = "tinyint(4) default 1")
    private Integer status;

    @OneToMany(mappedBy = "billNo")
    private List<BillDetail> details;

}
