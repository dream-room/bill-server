package com.dream.room.bill.common.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

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
        return getToken(token);
    }

    public Jws<Claims> getToken(WebRequest webRequest) {
        String token = webRequest.getHeader(config.getHeader());
        return getToken(token);
    }

    public Jws<Claims> getToken(String token) {
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
     * 创建token
     */
    public String createStringToken(String user) {
        String token = Jwts.builder()
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS256, config.getSecret())
                .setSubject(user)
                .setIssuer("bill-server")
                .setIssuedAt(new Date())
                .setAudience("bill-client")
                .setExpiration(Date.from(Instant.now().plusSeconds(config.getTimeout().getSeconds())))
                .compact();
        return config.getPrefix() + token;
    }
}
