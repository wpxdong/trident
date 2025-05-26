package io.github.trident.base.config;

import com.google.gson.Gson;
import io.github.trident.common.utils.GsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @projectName: trident
 * @package: io.github.trident.base.config
 * @className: TridentBaseConfiguration
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/5 23:16
 * @version: 1.0
 */
@Configuration
@PropertySource(value = "file:${server_config:conf/server.properties}")
public class TridentBaseConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Gson gson() {
        return GsonUtils.GSON;
    }
}
