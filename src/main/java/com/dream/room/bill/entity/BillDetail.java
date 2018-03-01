package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

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
