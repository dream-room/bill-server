package com.dream.room.bill.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;
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

    @Resource
    private RequestMatcher authTokenMatchers;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //静态资源放行
        if (PathRequest.toStaticResources().atCommonLocations().matches(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //OPTIONS不拦截
        if ("OPTIONS".equals(httpServletRequest.getMethod().toUpperCase())) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //部分权限相关的放行
        if (authTokenMatchers.matches(httpServletRequest)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        Jws<Claims> token = jwtAuthService.getToken(httpServletRequest);
        if (token == null) {
            httpServletResponse.sendRedirect("/auth/fail");
            return;
        }
        String subject = token.getBody().getSubject();
        log.error(subject);
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

}
