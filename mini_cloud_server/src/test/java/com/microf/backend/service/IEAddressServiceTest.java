package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.microf.backend.ServerConfigurationTest;
import com.microf.model.Address;
import com.microf.model.IEAddress;
import com.microf.model.builder.IEAddressBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.microf.backend.service.ServiceConstants.ADDRESS_MAP_IE;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class) @ContextConfiguration(classes = { ServerConfigurationTest.class }) public class IEAddressServiceTest {

    private final Logger log = LoggerFactory.getLogger(IEAddressServiceTest.class);

    private final IEAddress ieAddress1 = new IEAddressBuilder().setPostcode("BT274YW")
                                                               .setAddressline1("Hilden Court")
                                                               .setSummaryline("Hilden Court")
                                                               .setLatitude("54.78665")
                                                               .setLongitude("34.6789")
                                                               .createIEAddress();
    private final IEAddress ieAddress2 = new IEAddressBuilder().setPostcode("BT285YW")
                                                               .setAddressline1("SomeWhere Court")
                                                               .setSummaryline("SomeWhere Court")
                                                               .setLatitude("54.80665")
                                                               .setLongitude("34.6790")
                                                               .createIEAddress();
    private final IEAddress ieAddress3 = new IEAddressBuilder().setPostcode("BT284YW")
                                                               .setAddressline1("SomeWhere Hill")
                                                               .setSummaryline("SomeWhere Hill")
                                                               .setLatitude("54.78445")
                                                               .setLongitude("34.6556")
                                                               .createIEAddress();
    private final IEAddress ieAddress4 = new IEAddressBuilder().setPostcode("BT274YW")
                                                               .setAddressline1("Hilden Mill")
                                                               .setSummaryline("Hilden Mill")
                                                               .setLatitude("54.78665")
                                                               .setLongitude("34.6789")
                                                               .createIEAddress();

    @Autowired private HazelcastInstance hazelcastInstance;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    @Qualifier("IEAddressService")
    private IAddressService addressService;


    @Before public void before() throws Exception {
        hazelcastInstance.getMap(ADDRESS_MAP_IE).putIfAbsent(ieAddress1.hashCode(), ieAddress1);
        hazelcastInstance.getMap(ADDRESS_MAP_IE).putIfAbsent(ieAddress2.hashCode(), ieAddress2);
        hazelcastInstance.getMap(ADDRESS_MAP_IE).putIfAbsent(ieAddress4.hashCode(), ieAddress4);

    }

    @After public void after() throws Exception {
        hazelcastInstance.getMap(ADDRESS_MAP_IE).clear();
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test public void testGetAddressByCodeInMemory() throws Exception {
        List<Address> ieAddress = addressService.getAddressByCode("BT274YW");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(ieAddress1));
        assertThat(ieAddress, hasItem(ieAddress4));

    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test public void testGetAddressByCodeInServer() throws Exception {

        List<IEAddress> listAddress = Arrays.asList(ieAddress3);
        ResponseEntity response = ResponseEntity.ok(listAddress.toArray());

        when(restTemplate.getForEntity(anyString(), Mockito.<Class<IEAddress[]>>any(), Matchers.<Map<String, String>>any())).thenReturn(response);
        List<Address> ieAddress = addressService.getAddressByCode("BT284YW");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(ieAddress3));
        assertThat(ieAddress, not(hasItem(ieAddress2)));

    }

    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test public void testGetAddressGeoByCodeInMemory() throws Exception {
        List<Address> ieAddress = addressService.getAddressGeoByCode("BT274YW");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(ieAddress4));
        assertThat(ieAddress, hasItem(ieAddress1));

    }

    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test public void testGetAddressGeoByCodeInServer() throws Exception {
        List<IEAddress> listAddress = Arrays.asList(ieAddress3);
        ResponseEntity response = ResponseEntity.ok(listAddress.toArray());

        when(restTemplate.getForEntity(anyString(), Mockito.<Class<IEAddress[]>>any(), Matchers.<Map<String, String>>any())).thenReturn(response);
        List<Address> ieAddress = addressService.getAddressByCode("BT284YW");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(ieAddress3));
        assertThat(ieAddress, not(hasItem(ieAddress2)));
        assertThat(ieAddress.get(0).getLatitude(), is(notNullValue()));
        assertThat(ieAddress.get(0).getLongitude(), is(notNullValue()));

    }

    /**
     * Method: getAddressByCodeAndWhat3Words(String search_param)
     */
    @Test public void testGetAddressByCodeAndWhat3Words() throws Exception {

    }

    /**
     * Method: getAddressPositionByCode(String search_param)
     */
    @Test public void testGetAddressPositionByCode() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: getAddressByPosition(String latitude, String longitude, String range)
     */
    @Test public void testGetAddressByPosition() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: init()
     */
    @Test public void testInit() throws Exception {

        try {
            Method method = IEAddressServiceImpl.class.getMethod("init");
            method.setAccessible(true);
            method.invoke(addressService);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Unexpected error {}", e.getMessage(), e);
        }

    }

    /**
     * Method: fillCollections(final ResponseEntity<IEAddress[]> ieAddressJSON, final IMap<String,IEAddress> map, final List<Address> listAddress)
     */
    @Test public void testFillCollections() throws Exception {

        List<IEAddress> listIEAddress = Arrays.asList(ieAddress1, ieAddress2, ieAddress3);
        ResponseEntity response = ResponseEntity.ok(listIEAddress.toArray());

        IMap<String, IEAddress> map = hazelcastInstance.getMap("Testing");

        List<Address> listAddress = new ArrayList<>();

        try {
            Method method = IEAddressServiceImpl.class.getMethod("fillCollections", ResponseEntity.class, IMap.class, List.class);
            method.setAccessible(true);
            method.invoke(addressService, response, map, listAddress);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Unexpected error {}", e.getMessage(), e);
        }

    }

} 
