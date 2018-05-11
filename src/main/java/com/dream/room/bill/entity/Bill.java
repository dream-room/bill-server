package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"details"}, allowSetters = true)
@Entity
@ApiModel(value = "Bill", description = "账单")
public class Bill extends BaseEntity {
    @Column(unique = true, nullable = false, length = 64)
    @ApiModelProperty(value = "编号", required = true)
    private String no;

    @Column(nullable = false)
    @ApiModelProperty(value = "订单名称", required = true)
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(value = "公司", required = true)
    private String company;

    @Column(nullable = false, columnDefinition = "tinyint(4) default 1")
    @ApiModelProperty(value = "订单状态 1：编辑中 2：进行中 3：已完成 4：取消", required = true)
    private Integer status;

    @Column
    @ApiModelProperty(value = "订单确定时间（由编辑到进行中）", readOnly = true)
    private ZonedDateTime confirmTime;

    @Column
    @ApiModelProperty(value = "订单完成时间（由进行到完成）", readOnly = true)
    private ZonedDateTime doneTime;

    @Column
    @ApiModelProperty(value = "最后期盼发货时间")
    private ZonedDateTime lastExpectedTime;

    /*@OneToMany(mappedBy = "billNo")
    @ApiModelProperty(value = "账单明细", readOnly = true)
    private List<BillDetail> details;*/

}
