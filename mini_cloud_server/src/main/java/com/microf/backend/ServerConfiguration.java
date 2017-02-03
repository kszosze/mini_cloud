package com.microf.backend;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@SpringBootConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@Configuration
public class ServerConfiguration {
}
