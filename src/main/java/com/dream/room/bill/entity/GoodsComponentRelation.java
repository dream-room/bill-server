package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(indexes = {
        @Index(name = "un_goods_component", columnList = "goodsId,componentId", unique = true)
})
@ApiModel(value = "GoodsComponentRelation", description = "物品零件关联表")
public class GoodsComponentRelation extends BaseEntity {

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

    @Column(nullable = false)
    @ApiModelProperty(value = "物品id", required = true)
    private Long goodsId;

    @Column(nullable = false)
    @ApiModelProperty(value = "零件id", required = true)
    private Long componentId;

    @Column(nullable = false)
    @ApiModelProperty(value = "数量", required = true)
    private Integer num;
}