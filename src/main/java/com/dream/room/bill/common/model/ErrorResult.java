package com.dream.room.bill.common.model;

import com.dream.room.bill.common.BillException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
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
    private LocalDateTime time = LocalDateTime.now();

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("STATUS:");
        builder.append(StringUtils.isEmpty(status)?HttpStatus.INTERNAL_SERVER_ERROR.value():status.value());
        if (!StringUtils.isEmpty(code)){
            builder.append(" CODE:").append(code);
        }
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
