package com.dream.room.bill.common.model;

import com.dream.room.bill.common.BillException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/1/30.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

    private HttpStatus status;
    private String code;
    private String title;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time = LocalDateTime.now();

    @Override
    public String toString(){
        HttpStatus here = status;
        if (here == null) {
            here = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        StringBuilder builder = new StringBuilder("STATUS:");
        builder.append(here.getReasonPhrase());
        builder.append(" CODE:").append(StringUtils.isEmpty(code)?here.value():code);
        if (!StringUtils.isEmpty(title)){
            builder.append(" TITLE:").append(title);
        }
        if (!StringUtils.isEmpty(message)){
            builder.append(" \nMESSAGE:").append(message);
        }
        return builder.toString();
    }

    public BillException toException(){
        return new BillException(this);
    }

}
