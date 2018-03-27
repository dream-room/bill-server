package com.dream.room.bill.dto;

import com.dream.room.bill.entity.Component;
import com.dream.room.bill.entity.GoodsComponent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2018/3/28 0:04 End.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GoodsComponentResult", description = "物品零件明细结果模型")
public class GoodsComponentResultDto extends GoodsComponent {

    @ApiModelProperty(value = "零件详情")
    private Component component;

}
