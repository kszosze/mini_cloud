package com.microf.backend.util;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.instance.HazelcastInstanceFactoryTest;

import java.util.Objects;

public class HazelcastFactoryMock
{

    private HazelcastInstance hazelcastInstance;

    public HazelcastFactoryMock(final Config config) {
        if (Objects.isNull(hazelcastInstance)) {
            hazelcastInstance = new HazelcastInstanceFactoryTest().createHazelcastInstance();
        }
    }

    public HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }
}
