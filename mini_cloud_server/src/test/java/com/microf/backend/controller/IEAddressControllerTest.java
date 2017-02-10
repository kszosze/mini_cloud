package com.microf.backend.controller;

import com.microf.backend.ServerCommonConfigurationTest;
import com.microf.backend.ServerConfigurationTest;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServerCommonConfigurationTest.class, ServerConfigurationTest.class })
public class IEAddressControllerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getIEAddressByCode(@PathVariable("ierCode") String ierCode) 
* 
*/ 
@Test
public void testGetIEAddressByCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAddressAndGeoByCode(@PathVariable("ierCode") String ierCode, @RequestParam("addtags") String addTags) 
* 
*/ 
@Test
public void testGetAddressAndGeoByCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getIEPosition(@PathVariable("ierCode") String ierCode) 
* 
*/ 
@Test
public void testGetIEPosition() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getIEAddressFromPosition(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude) 
* 
*/ 
@Test
public void testGetIEAddressFromPosition() throws Exception { 
//TODO: Test goes here... 
} 


} 
