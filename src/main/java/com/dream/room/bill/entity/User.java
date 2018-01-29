package com.dream.room.bill.entity;

import com.dream.room.bill.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createTime;
    @UpdateTimestamp
    private Instant updateTime;

    //编号
    @Column(unique = true, nullable = false, length = 64)
    private String no;
    //用户名
    @Column(nullable = false)
    private String name;
    //密码
    @Column(nullable = false, updatable = false)
    private String password;
    //描述
    private String description;

}
