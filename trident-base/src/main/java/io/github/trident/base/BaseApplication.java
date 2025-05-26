package io.github.trident.base;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.CountDownLatch;

/**
 * @projectName: trident
 * @package: io.github.trident.base
 * @className: BaseApplication
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/2/19 21:52
 * @version: 1.0
 */
@SpringBootApplication
@EnableDubbo
public class BaseApplication {
    final static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
       System.out.println(org.apache.commons.codec.digest.DigestUtils.sha512Hex("admin123"));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                latch.countDown();
            }
        });

        SpringApplication.run(BaseApplication.class, args);
        System.out.println("AdsServiceImplApplication is running");
    }

    @EventListener
    void run(ApplicationReadyEvent event) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
