package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
/*@Table(indexes = {
        @Index(name = "un_goods_component", columnList = "goodsId,componentId", unique = true)
})*/
@ApiModel(value = "GoodsComponent", description = "物品零件关联表")
public class GoodsComponent extends BaseEntity {

    @Column(nullable = false)
    @ApiModelProperty(value = "物品id", required = true)
    private Long goodsId;

    @Column(nullable = false)
    @ApiModelProperty(value = "零件id", required = true)
    private Long componentId;

    @Column(nullable = false)
    @ApiModelProperty(value = "零件名称", required = true)
    private String componentName;

    @Column(nullable = false)
    @ApiModelProperty(value = "数量", required = true)
    private Integer num;

    @Column(nullable = false,  precision = 7, scale = 2)
    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;

}
