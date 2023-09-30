package com.config;

import org.redisson.config.SingleServerConfig;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfig {

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    public RedissonAutoConfigurationCustomizer redissonCustomizer() {
        return config -> {
            config.setThreads(redissonProperties.getThreads());
            config.setNettyThreads(redissonProperties.getNettyThreads());
            SingleServerConfig singleServerConfig = redissonProperties.getSingleServerConfig();
            /* 使用单机模式 */
            if (singleServerConfig != null) {
                config.useSingleServer()
                        .setClientName(singleServerConfig.getClientName())
                        .setConnectionMinimumIdleSize(singleServerConfig.getConnectionMinimumIdleSize())
                        .setConnectionPoolSize(singleServerConfig.getConnectionPoolSize())
                        .setIdleConnectionTimeout(singleServerConfig.getIdleConnectionTimeout())
                        .setTimeout(singleServerConfig.getTimeout())
                        .setRetryAttempts(singleServerConfig.getRetryAttempts())
                        .setRetryInterval(singleServerConfig.getRetryInterval())
                        .setSubscriptionConnectionPoolSize(singleServerConfig.getSubscriptionConnectionPoolSize());
            }
        };
    }

}
