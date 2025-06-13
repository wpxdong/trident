package io.github.trident.base.config;

import com.google.gson.Gson;
import io.github.trident.common.utils.GsonUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
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
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
//    @Bean
//    public LocaleResolver  localeResolver() {
//        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
//        localeResolver.setSupportedLocales(Stream.of(Locale.US, Locale.SIMPLIFIED_CHINESE)
//                .collect(Collectors.toList()));
//        return localeResolver;
//    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Gson gson() {
        return GsonUtils.GSON;
    }
}
