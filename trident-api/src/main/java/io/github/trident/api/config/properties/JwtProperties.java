package io.github.trident.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @projectName: trident
 * @package: io.github.trident.api.config.properties
 * @className: JwtProperties
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/1 20:29
 * @version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "trident.jwt")
public class JwtProperties {
    private Long expiredSeconds = 24 * 60 * 60 * 1000L;

    public Long getExpiredSeconds() {
        return expiredSeconds;
    }

    public void setExpiredSeconds(Long expiredSeconds) {
        this.expiredSeconds = expiredSeconds;
    }
}
