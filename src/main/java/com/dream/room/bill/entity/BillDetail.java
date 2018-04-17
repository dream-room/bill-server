package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ApiModel(value = "BillDetail", description = "账单明细")
public class BillDetail extends BaseEntity {

    @Column(nullable = false, length = 64)
    @ApiModelProperty(value = "订单编号", required = true)
    private String billNo;

    @Column(nullable = false)
    @ApiModelProperty(value = "物品ID", required = true)
    private Long goodsId;

    @Column(nullable = false)
    @ApiModelProperty(value = "物品版本", readOnly = true)
    private Integer goodsVersion;

    @Column(nullable = false)
    @ApiModelProperty(value = "物品名称", readOnly = true)
    private String goodsName;

    @Column(nullable = false)
    @ApiModelProperty(value = "数量", required = true)
    private Integer amount;

    @Column(nullable = false,  precision = 7, scale = 2)
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @Column(nullable = false,  precision = 9, scale = 2)
    @ApiModelProperty(value = "总价", readOnly = true)
    private BigDecimal total;

    @Column(nullable = false, columnDefinition = "tinyint(4) default 5")
    @ApiModelProperty(value = "优先级（1-9），越小优先级越高，默认5")
    private Integer priority;

    @Column(nullable = false, columnDefinition = "tinyint(4) default 1")
    @ApiModelProperty(value = "订单状态 1：编辑中 2：待发货 3：已发货 4：取消发货", readOnly = true)
    private Integer status;

    @Column
    @ApiModelProperty(value = "订单完成时间（已发货）", readOnly = true)
    private ZonedDateTime doneTime;

}
