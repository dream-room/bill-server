package com.dream.room.bill.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/1/22.
 */
@Data
public class Result<T> {

    private String code;
    private String title;
    private String message;
    private T object;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();

    public enum Code{
        SUCCESS("0x000000","成功"),
        SUCCESS_GET("0x000001","查询成功"),
        SUCCESS_PUT("0x000002","修改成功"),
        SUCCESS_POST("0x000003","添加成功"),
        SUCCESS_DELETE("0x000004","删除成功"),
        UN_KNOWN_ERROR("9x999999","未知错误");

        private String code;
        private String message;

        Code(String code,String message){
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public Result() {
    }

    public Result(Code code) {
        this(code,null);
    }

    public Result(T object) {
        this(Code.SUCCESS,object);
    }

    public Result(Code code, T object) {
        setResultCode(code);
        this.object = object;
    }

    public void setResultCode(Code code) {
        this.code = code.getCode();
        this.title = code.getMessage();
    }
}
