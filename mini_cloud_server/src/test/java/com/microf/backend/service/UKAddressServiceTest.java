package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.microf.backend.ServerConfigurationTest;
import com.microf.model.Address;
import com.microf.model.UKAddress;
import com.microf.model.builder.UKAddressBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.microf.backend.service.ServiceConstants.ADDRESS_MAP_UK;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServerConfigurationTest.class})
public class UKAddressServiceTest {

    private final Logger log = LoggerFactory.getLogger(UKAddressServiceTest.class);


    private final UKAddress address1 = new UKAddressBuilder().setPostcode("NR14 7ZZ")
            .setAddressline1("Allies Computing Ltd")
            .setSummaryline("Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ")
            .setLatitude("52.5859889436")
            .setLongitude("1.3492353929")
            .setPosttown("Norfolk")
            .createUKAddress();

    private final UKAddress address2 = new UKAddressBuilder().setPostcode("NR14 7ZZ")
            .setAddressline1("B 2 B Cashflow Solutions Ltd")
            .setSummaryline("Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ")
            .setLatitude("52.5859889436")
            .setLongitude("1.3492353929")
            .setPosttown("Norfolk")
            .createUKAddress();

    private final UKAddress address3 = new UKAddressBuilder().setPostcode("NR10 7PZ")
            .setAddressline1("Brasteds Event Excellence")
            .setSummaryline("Brasteds Event Excellence, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ")
            .setLatitude("52.5859889436")
            .setLongitude("1.3492353929")
            .setPosttown("Norfolk")
            .createUKAddress();

    private final UKAddress address4 = new UKAddressBuilder().setPostcode("NR14 7ZZ")
            .setAddressline1("Brasteds Lodge")
            .setSummaryline("Brasteds Lodge, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ")
            .setLatitude("52.5859889436")
            .setLongitude("1.3492353929")
            .setPosttown("Norfolk")
            .createUKAddress();

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("UKAddressService")
    private IAddressService addressService;

    @Before
    public void before() throws Exception {
        hazelcastInstance.getMap(ADDRESS_MAP_UK).putIfAbsent(address1.hashCode(), address1);
        hazelcastInstance.getMap(ADDRESS_MAP_UK).putIfAbsent(address2.hashCode(), address2);
        hazelcastInstance.getMap(ADDRESS_MAP_UK).putIfAbsent(address4.hashCode(), address4);
    }

    @After
    public void after() throws Exception {
        hazelcastInstance.getMap(ADDRESS_MAP_UK).clear();
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test
    public void testGetAddressByCodeInMemory() throws Exception {
        List<Address> ukAddress = addressService.getAddressByCode("NR14 7ZZ");

        assertThat(ukAddress, is(not(empty())));
        assertThat(ukAddress, hasItem(address1));
        assertThat(ukAddress, hasItem(address4));
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test
    public void testGetAddressByCodeInServer() throws Exception {

        List<UKAddress> listAddress = Arrays.asList(address3);
        ResponseEntity response = ResponseEntity.ok(listAddress.toArray());

        when(restTemplate.getForEntity(anyString(), Mockito.<Class<UKAddress[]>>any(), Matchers.<Map<String, String>>any())).thenReturn(response);
        List<Address> ieAddress = addressService.getAddressByCode("NR10 7PZ");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(address3));
        assertThat(ieAddress, not(hasItem(address2)));

    }

    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test
    public void testGetAddressGeoByCodeInServer() throws Exception {
        List<UKAddress> listAddress = Arrays.asList(address3);
        ResponseEntity response = ResponseEntity.ok(listAddress.toArray());

        when(restTemplate.getForEntity(anyString(), Mockito.<Class<UKAddress[]>>any(), Matchers.<Map<String, String>>any())).thenReturn(response);
        List<Address> ieAddress = addressService.getAddressByCode("NR10 7PZ");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress, hasItem(address3));
        assertThat(ieAddress, not(hasItem(address2)));
        assertThat(ieAddress.get(0).getLatitude(), is(notNullValue()));
        assertThat(ieAddress.get(0).getLongitude(), is(notNullValue()));

    }

    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test
    public void testGetAddressGeoByCodeInMemory() throws Exception {
        List<Address> ukAddress = addressService.getAddressByCode("NR14 7ZZ");

        assertThat(ukAddress, is(not(empty())));
        assertThat(ukAddress, hasItem(address1));
        assertThat(ukAddress, hasItem(address4));
    }


    /**
     * Method: init()
     */
    @Test
    public void testInit() throws Exception {

        try {
            Method method = UKAddressServiceImpl.class.getMethod("init");
            method.setAccessible(true);
            method.invoke(addressService);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Unexpected error {}", e.getMessage(), e);
        }

    }

    /**
     * Method: fillCollections(final ResponseEntity<IEAddress[]> ieAddressJSON, final IMap<String,IEAddress> map, final List<Address> listAddress)
     */
    @Test
    public void testFillCollections() throws Exception {

        List<UKAddress> listIEAddress = Arrays.asList(address1, address2, address3);
        ResponseEntity response = ResponseEntity.ok(listIEAddress.toArray());

        IMap<String, UKAddress> map = hazelcastInstance.getMap("Testing");

        List<Address> listAddress = new ArrayList<>();

        try {
            Method method = UKAddressServiceImpl.class.getMethod("fillCollections", ResponseEntity.class, IMap.class, List.class);
            method.setAccessible(true);
            method.invoke(addressService, response, map, listAddress);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("Unexpected error {}", e.getMessage(), e);
        }

    }

} 
