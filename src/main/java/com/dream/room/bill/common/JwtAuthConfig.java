package com.dream.room.bill.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

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
    private Duration timeout = Duration.ofHours(10L);

}
