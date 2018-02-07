package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class BillDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createTime;
    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updateTime;

    //订单编号
    @Column(nullable = false, length = 64)
    private String billNo;
    //存货编号
    @Column(nullable = false, length = 64)
    private String productNo;
    //产品名称
    @Column(nullable = false)
    private String productName;
    //规格型号
    @Column(nullable = false)
    private String productModel;
    //数量
    @Column(nullable = false)
    private Integer amount;
    //单价
    @Column(nullable = false,  precision = 7, scale = 2)
    private BigDecimal unitPrice;
    //单价
    @Column(nullable = false,  precision = 9, scale = 2)
    private BigDecimal total;

}
