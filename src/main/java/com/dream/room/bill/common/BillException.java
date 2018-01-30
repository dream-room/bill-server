package com.dream.room.bill.common;

import com.dream.room.bill.common.model.ErrorResult;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 20:04 End.
 */
public class BillException extends RuntimeException {

    private ErrorResult result;

    public BillException(ErrorResult result){
        super(result.toString());
        this.result = result;
    }

    public ErrorResult getResult() {
        return result;
    }
}
