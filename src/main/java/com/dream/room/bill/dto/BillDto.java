package com.dream.room.bill.dto;

import com.dream.room.bill.common.PageQueryDto;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/5/4.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BillDto extends PageQueryDto {

    @ApiParam(value = "编号")
    private String no;
    @ApiParam(value = "名称")
    private String name;
    @ApiParam(value = "公司")
    private String company;
    @ApiParam(value = "订单状态 1：编辑中 2：进行中 3：已完成 4：取消")
    private Integer status;
    @ApiParam(value = "创建起始时间")
    private Instant startTime;
    @ApiParam(value = "创建结束时间")
    private Instant endTime;

}
