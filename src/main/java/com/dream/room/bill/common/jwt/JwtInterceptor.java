package com.dream.room.bill.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/5/15.
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private JwtAuthService jwtAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Jws<Claims> token = jwtAuthService.getToken(request);
        if (token == null) {
            try {
                response.sendRedirect(request.getContextPath() + "/auth/fail");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        String subject = token.getBody().getSubject();
        log.info(subject + ":" + token.getBody().getIssuedAt().toString());
        return false;
    }
}
