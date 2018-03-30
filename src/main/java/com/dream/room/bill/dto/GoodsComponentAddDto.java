package com.dream.room.bill.dto;

import com.dream.room.bill.entity.Component;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/30.
 */
@Data
@ApiModel(value = "GoodsComponentAdd", description = "物品添加模型")
public class GoodsComponentAddDto {

    @ApiModelProperty(value = "物品ID")
    private Long goodsId;
    @ApiModelProperty(value = "零件列表")
    private List<Component> components;

}
