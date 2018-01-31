package com.dream.room.bill.common;

import lombok.Data;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/31.
 */
@Data
public class PageQueryDto {

    private int page = 0;
    private int size = 10;
    private String sort;
    private String direction;//desc,asc

}
