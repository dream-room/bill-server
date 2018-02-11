package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"details"}, allowSetters = true)
@Entity
public class Bill extends BaseEntity {

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

    @Column(unique = true, nullable = false, length = 64)
    @ApiModelProperty(value = "编号", required = true)
    private String no;

    @Column(nullable = false)
    @ApiModelProperty(value = "订单名称", required = true)
    private String name;

    @Column(nullable = false, columnDefinition = "tinyint(4) default 1")
    @ApiModelProperty(value = "订单状态 1：正常 2：作废", required = true)
    private Integer status;

    @OneToMany(mappedBy = "billNo")
    @ApiModelProperty(value = "账单明细", readOnly = true)
    private List<BillDetail> details;

}
