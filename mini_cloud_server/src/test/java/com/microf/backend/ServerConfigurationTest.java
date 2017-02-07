package com.microf.backend;

import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.util.HazelcastFactoryMock;
import org.junit.Ignore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
@Ignore
public class ServerConfigurationTest {

    @Bean
    public HazelcastInstance getHazelcastInstance() {
        return new HazelcastFactoryMock().getHazelcastInstance();
    }

}
