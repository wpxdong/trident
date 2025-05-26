package io.github.trident.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @projectName: trident
 * @package: io.github.trident.api.config.properties
 * @className: ShiroProperties
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/1 12:29
 * @version: 1.0
 */
@Component("shiroProperties")
@ConfigurationProperties(prefix = "trident.shiro")
public class ShiroProperties {
    private List<String> whiteList;

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}
