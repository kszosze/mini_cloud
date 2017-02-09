package com.microf.backend.controller;

import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.ServerCommonConfigurationTest;
import com.microf.backend.ServerConfigurationTest;
import com.microf.backend.service.IAddressService;
import com.microf.backend.service.UKAddressServiceImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServerCommonConfigurationTest.class, ServerConfigurationTest.class })
public class UKAddressControllerTest {

    private final Logger log = LoggerFactory.getLogger(UKAddressControllerTest.class);

    @Autowired private HazelcastInstance hazelcastInstance;

    private IAddressService addressService;

    @Mock private RestTemplate restTemplate;

@Before
public void before() throws Exception {
    addressService = new UKAddressServiceImpl(hazelcastInstance, restTemplate);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getIEAddressByCode(@PathVariable("ukCode") String ierCode) 
* 
*/ 
@Test
public void testGetIEAddressByCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAddressAndGeoByCode(@PathVariable("ukCode") String ierCode) 
* 
*/ 
@Test
public void testGetAddressAndGeoByCode() throws Exception { 
//TODO: Test goes here... 
} 


} 
