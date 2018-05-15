package com.dream.room.bill.common.jwt;

import com.dream.room.bill.common.BillException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.annotation.Resource;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/5/15.
 */
@Slf4j
//@Component
public class JwtWebRequestInterceptor implements WebRequestInterceptor {

    @Resource
    private JwtAuthService jwtAuthService;

    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        Jws<Claims> token = jwtAuthService.getToken(webRequest);
        if (token == null) {
            throw BillException.of(HttpStatus.UNAUTHORIZED,"Token验证失败","可能Token过期，请重新登录，获取Token！");
        }
        String subject = token.getBody().getSubject();
        log.info(subject + ":" + token.getBody().getIssuedAt().toString());
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }

}
