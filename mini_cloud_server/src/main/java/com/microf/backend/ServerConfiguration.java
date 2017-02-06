package com.microf.backend;

import com.hazelcast.config.Config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableCaching
@Configuration
public class ServerConfiguration {

    @Bean
    public Config config() {
        return new Config().setProperty("hazelcast.logging.type","slf4j"); // Set up any non-default config here
    }

}
