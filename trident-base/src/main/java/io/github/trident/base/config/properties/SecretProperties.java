package io.github.trident.base.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @projectName: trident
 * @package: io.github.trident.base.config.properties
 * @className: SecretProperties
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/31 21:20
 * @version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "trident.aes.secret")
public class SecretProperties {
    private String key;
    private String iv;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
