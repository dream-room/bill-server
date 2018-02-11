package com.dream.room.bill.common;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/31.
 */
@Data
public class PageQueryDto {

    @ApiParam(value = "页数", defaultValue = "0")
    private int page = 0;
    @ApiParam(value = "行数", defaultValue = "10")
    private int size = 10;
    @ApiParam(value = "排序项")
    private String sort;
    @ApiParam(value = "升序/降序", allowableValues = "desc,asc")
    private String direction;

}
