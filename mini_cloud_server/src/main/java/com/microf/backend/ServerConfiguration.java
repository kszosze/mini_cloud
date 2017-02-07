package com.microf.backend;

import com.hazelcast.config.Config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableCaching
@Configuration
@ComponentScan(basePackages="com.microf")
public class ServerConfiguration {

    @Bean
    public Config config() {
        return new Config().setProperty("hazelcast.logging.type","slf4j"); // Set up any non-default config here
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
