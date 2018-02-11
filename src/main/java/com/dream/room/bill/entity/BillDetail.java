package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "BillDetail", description = "账单明细")
public class BillDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", readOnly = true)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @ApiModelProperty(value = "创建时间", readOnly = true)
    private Instant createTime;
    @UpdateTimestamp
    @Column(nullable = false)
    @ApiModelProperty(value = "修改时间", readOnly = true)
    private Instant updateTime;

    @Column(nullable = false, length = 64)
    @ApiModelProperty(value = "订单编号", required = true)
    private String billNo;

    @Column(nullable = false, length = 64)
    @ApiModelProperty(value = "存货编号", required = true)
    private String productNo;

    @Column(nullable = false)
    @ApiModelProperty(value = "产品名称", required = true)
    private String productName;

    @Column(nullable = false)
    @ApiModelProperty(value = "规格型号", required = true)
    private String productModel;

    @Column(nullable = false)
    @ApiModelProperty(value = "数量", required = true)
    private Integer amount;

    @Column(nullable = false,  precision = 7, scale = 2)
    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;

    @Column(nullable = false,  precision = 9, scale = 2)
    @ApiModelProperty(value = "总价", readOnly = true)
    private BigDecimal total;

}
