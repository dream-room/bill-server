package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(indexes = {
        @Index(name = "un_goods_version", columnList = "goodsId,version", unique = true)
})
@ApiModel(value = "GoodsArchive", description = "物品历史")
public class GoodsArchive extends BaseEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "物品ID", required = true)
    private Long goodsId;

    @Column(nullable = false)
    @ApiModelProperty(value = "版本", readOnly = true)
    private Integer version;

    @Column(nullable = false)
    @ApiModelProperty(value = "物品名称", readOnly = true)
    private String name;

    @Column(columnDefinition = "text")
    @ApiModelProperty(value = "物品零件明细", readOnly = true)
    private String goodsDetail;

    @Column(nullable = false,  precision = 9, scale = 2, updatable = false)
    @ApiModelProperty(value = "价格", readOnly = true)
    private BigDecimal price;
}
