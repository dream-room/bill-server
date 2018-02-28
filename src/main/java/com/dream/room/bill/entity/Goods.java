package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"goodsComponents"}, allowSetters = true)
@Entity
@ApiModel(value = "Goods", description = "物品")
public class Goods extends BaseEntity {

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
    @ApiModelProperty(value = "物品名称", required = true)
    private String name;

    @Column(nullable = false, columnDefinition = "tinyint(4) default 1")
    @ApiModelProperty(value = "状态 1：正常 2：禁用", required = true)
    private Integer status;


    @OneToMany(mappedBy = "goodsId")
    @ApiModelProperty(value = "零件明细", readOnly = true)
    private List<GoodsComponent> goodsComponents;
}
