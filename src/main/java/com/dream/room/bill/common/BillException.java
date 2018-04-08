package com.dream.room.bill.common;

import com.dream.room.bill.common.model.ErrorResult;
import org.springframework.http.HttpStatus;

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

    public static BillException ofServerError(String title, String message){
        return of(HttpStatus.INTERNAL_SERVER_ERROR, title, message);
    }

    public static BillException ofBadRequest(String title, String message){
        return of(HttpStatus.BAD_REQUEST, title, message);
    }

    public static BillException ofNotFound(String title, String message){
        return of(HttpStatus.NOT_FOUND, title, message);
    }

    public static BillException of(HttpStatus status, String title, String message){
        return ErrorResult.builder()
                .status(status)
                .title(title)
                .message(message)
                .build()
                .toException();
    }

}
