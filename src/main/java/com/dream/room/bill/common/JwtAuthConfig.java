package com.dream.room.bill.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/3/9.
 */
@Data
@Component
@ConfigurationProperties("jwt")
public class JwtAuthConfig {

    private String header = "Authorization";
    private String prefix = "Bearer ";
    private String secret = "secret";

}
