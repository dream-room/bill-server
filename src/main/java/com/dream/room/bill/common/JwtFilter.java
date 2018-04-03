package com.dream.room.bill.common;

import com.dream.room.bill.common.model.ErrorResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/6.
 */
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Resource
    private JwtAuthService jwtAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Jws<Claims> token = jwtAuthService.getToken(httpServletRequest);
        if (token == null) {
            throw ErrorResult.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .title("授权失败")
                    .message("未包含授权信息")
                    .build().toException();
        }
        String subject = token.getBody().getSubject();
        log.error(subject);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

}
