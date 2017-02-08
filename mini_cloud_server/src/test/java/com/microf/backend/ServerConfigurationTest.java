package com.microf.backend;

import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.util.HazelcastFactoryMock;
import org.mockito.Mockito;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(value = {"com.microf.backend.service", "com.microf.backend.controller"})
public class ServerConfigurationTest {

    @Bean
    public HazelcastInstance getHazelcastInstance() {
        return new HazelcastFactoryMock().getHazelcastInstance();
    }

    @Bean
    public RestTemplate getSomeDependency() {
        // Mockito used here for mocking dependency
        return Mockito.mock(RestTemplate.class);
    }

    /**
     * Loads the test YML file for testing the spring wiring.
     *
     * @return the property placeholder.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        final YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("address-server.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
