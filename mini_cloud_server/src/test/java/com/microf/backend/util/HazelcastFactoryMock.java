package com.microf.backend.util;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceFactoryTest;

public class HazelcastFactoryMock extends HazelcastInstanceFactoryTest {

    private HazelcastInstance hazelcastInstance;

    public HazelcastFactoryMock() {
        super();
        Config config = new Config("ConfigTestHazelcast").setProperty("hazelcast.logging.type", "slf4j2");
        hazelcastInstance = new HazelcastInstanceFactoryTest().createHazelcastInstance(config);
    }

    public HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
