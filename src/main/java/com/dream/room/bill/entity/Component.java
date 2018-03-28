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
 * 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ApiModel(value = "Component", description = "零件")
public class Component extends BaseEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "零件名称", required = true)
    private String name;

    @Column(nullable = false, columnDefinition = "tinyint(4) default 1")
    @ApiModelProperty(value = "类型，1：机芯，2：气压表，3：压力表", required = true)
    private Integer type;

    @Column(nullable = false)
    @ApiModelProperty(value = "库存", required = true)
    private Integer num;

    @Column(nullable = false,  precision = 7, scale = 2)
    @ApiModelProperty(value = "通常价", required = true)
    private BigDecimal price;

}
