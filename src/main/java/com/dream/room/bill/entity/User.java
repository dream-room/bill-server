package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@Entity
@ApiModel(value = "User", description = "用户")
public class User extends BaseEntity {

    @Column(unique = true, nullable = false, length = 64)
    @ApiModelProperty(value = "编号", required = true)
    private String no;

    @Column(nullable = false)
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @Column(nullable = false, updatable = false)
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @Column
    @ApiModelProperty(value = "描述", allowEmptyValue = true)
    private String description;

}
