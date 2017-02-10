package com.microf.backend;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.util.HazelcastFactoryMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServerCommonConfigurationTest {


    @Bean
    public Config getConfig() {
        return new Config("ConfigTestHazelcast").setProperty("hazelcast.logging.type", "slf4j2");
    }

    @Bean
    public HazelcastInstance getHazelcastInstance(@Autowired final Config config) {
        return new HazelcastFactoryMock(config).getHazelcastInstance();
    }
}
