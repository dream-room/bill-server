package com.dream.room.bill.common;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/7.
 */
@Slf4j
@Service
public class JwtAuthService {

    @Resource
    private JwtAuthConfig config;

    @Resource
    private HttpServletRequest request;

    public String getStringToken() {
        return request.getHeader(config.getHeader());
    }

    public Jws<Claims> getToken() {
        return getToken(request);
    }

    public Jws<Claims> getToken(HttpServletRequest request) {
        String token = request.getHeader(config.getHeader());
        if (token == null || !token.startsWith(config.getPrefix())) {
            return null;
        }
        token = token.substring(config.getPrefix().length());
        try {
            return Jwts.parser()
                    .setSigningKey(config.getSecret())
                    .parseClaimsJws(token);
        } catch (SignatureException e){
            log.error("签名错误！");
        }
        return null;
    }


    /**
     * 创建token，测试用
     */
    public String createStringToken(String user) {
        String token = Jwts.builder()
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS256, config.getSecret())
                .claim("user", user)
                .compact();
        return config.getPrefix() + token;
    }

}
