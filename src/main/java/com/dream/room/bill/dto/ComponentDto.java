package com.dream.room.bill.dto;

import com.dream.room.bill.common.PageQueryDto;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/2/28.
 */
@Data
public class ComponentDto extends PageQueryDto {

    @ApiParam(value = "名称")
    private String name;

}
