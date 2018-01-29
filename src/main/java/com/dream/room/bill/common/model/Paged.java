package com.dream.room.bill.common.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/9/30.
 */
public class Paged implements Pageable{
    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
