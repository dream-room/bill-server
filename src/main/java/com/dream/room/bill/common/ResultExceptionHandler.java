package com.dream.room.bill.common;

import com.dream.room.bill.common.model.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 22:55 End.
 */
@Slf4j
@ControllerAdvice
public class ResultExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorResult> handle(RuntimeException e){
        ErrorResult result;
        if (e instanceof BillException){
            result = ((BillException) e).getResult();
        } else {
            log.error(e.getMessage());
            result = ErrorResult.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .title("系统异常！")
                    .message(e.getMessage())
                    .build();
        }
        if (StringUtils.isEmpty(result.getCode())){
            result.setCode(result.getStatus().toString());
        }
        return new ResponseEntity<>(result, result.getStatus());
    }

}
