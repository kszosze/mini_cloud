package com.microf.backend.service;

import com.microf.backend.ServerConfigurationTest;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ServerConfigurationTest.class})
public class IEAddressServiceTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getAddressByCode(String search_param) 
* 
*/ 
@Test
public void testGetAddressByCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAddressGeoByCode(String search_param) 
* 
*/ 
@Test
public void testGetAddressGeoByCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAddressByCodeAndWhat3Words(String search_param) 
* 
*/ 
@Test
public void testGetAddressByCodeAndWhat3Words() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAddressPositionByCode(String search_param) 
* 
*/ 
@Test
public void testGetAddressPositionByCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAddressByPosition(String latitude, String longitude, String range) 
* 
*/ 
@Test
public void testGetAddressByPosition() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: init() 
* 
*/ 
@Test
public void testInit() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = IEAddressServiceImpl.getClass().getMethod("init"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: fillCollections(final ResponseEntity<IEAddress[]> ieAddressJSON, final IMap<String,IEAddress> map, final List<Address> listAddress) 
* 
*/ 
@Test
public void testFillCollections() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = IEAddressServiceImpl.getClass().getMethod("fillCollections", final.class, final.class, final.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
