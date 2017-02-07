package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.ServerConfigurationTest;
import com.microf.model.Address;
import com.microf.model.IEAddress;
import com.microf.model.builder.IEAddressBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.microf.backend.service.ServiceConstants.ADDRESS_MAP_IE;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServerConfigurationTest.class})
public class IEAddressServiceTest {


    private final IEAddress ieAddress1 = new IEAddressBuilder().setPostcode("BT274YW").setAddressline1("Hilden Court").setSummaryline("Hilden Court").createIEAddress();
    private final IEAddress ieAddress2 = new IEAddressBuilder().setPostcode("BT285YW").setAddressline1("SomeWhere Court").setSummaryline("SomeWhere Court").createIEAddress();

    @Autowired
    private HazelcastInstance hazelcastInstance;

    private IAddressService addressService;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void before() throws Exception {
        addressService = new IEAddressServiceImpl(hazelcastInstance, restTemplate);
        hazelcastInstance.getMap(ADDRESS_MAP_IE).putIfAbsent(ieAddress1.getPostcode(), ieAddress1);
        hazelcastInstance.getMap(ADDRESS_MAP_IE).putIfAbsent(ieAddress2.getPostcode(), ieAddress2);

    }

    @After
    public void after() throws Exception {
        hazelcastInstance.getMap(ADDRESS_MAP_IE).clear();
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test
    public void testGetAddressByCodeInMemory() throws Exception {
        List<Address> ieAddress = addressService.getAddressByCode("BT274YW");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(ieAddress1));
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test
    public void testGetAddressByCodeInServer() throws Exception {
        List<Address> ieAddress = addressService.getAddressByCode("BT284YW");

        List<Address> listAddress = Arrays.asList(ieAddress1, ieAddress2);
        ResponseEntity response = ResponseEntity.ok(listAddress);

        when(restTemplate.getForEntity(anyString(), Mockito.<Class<IEAddress[]>> any(), Matchers.<Map<String, String>>any())).thenReturn(response);

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(ieAddress1));
        assertThat(ieAddress, hasItem(ieAddress2));

    }

    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test
    public void testGetAddressGeoByCode() throws Exception {

    }

    /**
     * Method: getAddressByCodeAndWhat3Words(String search_param)
     */
    @Test
    public void testGetAddressByCodeAndWhat3Words() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAddressPositionByCode(String search_param)
     */
    @Test
    public void testGetAddressPositionByCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAddressByPosition(String latitude, String longitude, String range)
     */
    @Test
    public void testGetAddressByPosition() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: init()
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
     * Method: fillCollections(final ResponseEntity<IEAddress[]> ieAddressJSON, final IMap<String,IEAddress> map, final List<Address> listAddress)
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
