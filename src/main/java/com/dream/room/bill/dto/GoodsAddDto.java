package com.dream.room.bill.dto;

import com.dream.room.bill.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/1.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GoodsAdd", description = "物品添加模型")
public class GoodsAddDto extends Goods {

    @ApiModelProperty(value = "零件Id列表")
    private List<Long> componentsIds;

}
