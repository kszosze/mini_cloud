package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.ServerCommonConfigurationTest;
import com.microf.backend.ServerConfigurationIntegrationTest;
import com.microf.model.Address;
import com.microf.model.IEAddress;
import com.microf.model.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServerCommonConfigurationTest.class, ServerConfigurationIntegrationTest.class })
public class IEAddressServiceIntegrationTest {

    private final Logger log = LoggerFactory.getLogger(IEAddressServiceIntegrationTest.class);

    @Autowired private HazelcastInstance hazelcastInstance;

    @Autowired private RestTemplate restTemplate;

    @Autowired
    @Qualifier("IEAddressService")
    private IAddressService addressService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Before public void before() throws Exception {
    }

    @After public void after() throws Exception {
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test public void testGetAddressByCode() throws Exception {


        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/addressgeo/ie/D02RY98?format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"summaryline\": \"Irish Pension Trust, 25-28 Marsh House, Adelaide Road, Dublin 2, D02 RY98\",\n" +
                        "        \"organisation\": \"Irish Pension Trust\",\n" +
                        "        \"buildingname\": \"Marsh House\",\n" +
                        "        \"number\": \"25-28\",\n" +
                        "        \"premise\": \"Marsh House, 25-28\",\n" +
                        "        \"street\": \"Adelaide Road\",\n" +
                        "        \"posttown\": \"Dublin 2\",\n" +
                        "        \"county\": \"Dublin\",\n" +
                        "        \"postcode\": \"D02 RY98\",\n" +
                        "        \"latitude\": \"53.332165\",\n" +
                        "        \"longitude\": \"-6.256094\",\n" +
                        "        \"what3words\": \"dunes.chart.handle\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));

        List<Address> ieAddress = addressService.getAddressByCode("D02RY98");

        assertThat(ieAddress, is(not(empty())));
        assertTrue(ieAddress.get(0) instanceof IEAddress);

    }

    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test public void testGetAddressGeoByCode() throws Exception {


        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/addressgeo/ie/D02RY98?format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"summaryline\": \"Irish Pension Trust, 25-28 Marsh House, Adelaide Road, Dublin 2, D02 RY98\",\n" +
                        "        \"organisation\": \"Irish Pension Trust\",\n" +
                        "        \"buildingname\": \"Marsh House\",\n" +
                        "        \"number\": \"25-28\",\n" +
                        "        \"premise\": \"Marsh House, 25-28\",\n" +
                        "        \"street\": \"Adelaide Road\",\n" +
                        "        \"posttown\": \"Dublin 2\",\n" +
                        "        \"county\": \"Dublin\",\n" +
                        "        \"postcode\": \"D02 RY98\",\n" +
                        "        \"latitude\": \"53.332165\",\n" +
                        "        \"longitude\": \"-6.256094\",\n" +
                        "        \"what3words\": \"dunes.chart.handle\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));
        List<Address> ieAddress = addressService.getAddressGeoByCode("BT274YW");

        assertThat(ieAddress, is(not(empty())));


    }

    /**
     * Method: getAddressByCodeAndWhat3Words(String search_param)
     */
    @Test public void testGetAddressByCodeAndWhat3Words() throws Exception {

        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/addressgeo/ie/D02RY98?format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"summaryline\": \"Irish Pension Trust, 25-28 Marsh House, Adelaide Road, Dublin 2, D02 RY98\",\n" +
                        "        \"organisation\": \"Irish Pension Trust\",\n" +
                        "        \"buildingname\": \"Marsh House\",\n" +
                        "        \"number\": \"25-28\",\n" +
                        "        \"premise\": \"Marsh House, 25-28\",\n" +
                        "        \"street\": \"Adelaide Road\",\n" +
                        "        \"posttown\": \"Dublin 2\",\n" +
                        "        \"county\": \"Dublin\",\n" +
                        "        \"postcode\": \"D02 RY98\",\n" +
                        "        \"latitude\": \"53.332165\",\n" +
                        "        \"longitude\": \"-6.256094\",\n" +
                        "        \"what3words\": \"dunes.chart.handle\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));

        List<Address> ieAddress = addressService.getAddressByCodeAndWhat3Words("BT284YW");

        assertThat(ieAddress, is(not(empty())));
        assertThat(ieAddress.get(0).getLatitude(), is(notNullValue()));
        assertThat(ieAddress.get(0).getLongitude(), is(notNullValue()));
    }

    /**
     * Method: getAddressPositionByCode(String search_param)
     */
    @Test public void testGetAddressPositionByCode() throws Exception {

        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/addressgeo/ie/D02RY98?format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"summaryline\": \"Irish Pension Trust, 25-28 Marsh House, Adelaide Road, Dublin 2, D02 RY98\",\n" +
                        "        \"organisation\": \"Irish Pension Trust\",\n" +
                        "        \"buildingname\": \"Marsh House\",\n" +
                        "        \"number\": \"25-28\",\n" +
                        "        \"premise\": \"Marsh House, 25-28\",\n" +
                        "        \"street\": \"Adelaide Road\",\n" +
                        "        \"posttown\": \"Dublin 2\",\n" +
                        "        \"county\": \"Dublin\",\n" +
                        "        \"postcode\": \"D02 RY98\",\n" +
                        "        \"latitude\": \"53.332165\",\n" +
                        "        \"longitude\": \"-6.256094\",\n" +
                        "        \"what3words\": \"dunes.chart.handle\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));

        List<Position> ieAddress = addressService.getAddressPositionByCode("BT284YW");

        assertThat(ieAddress, is(not(empty())));
        assertNotNull(ieAddress.get(0).getLatitude());
        assertEquals(ieAddress.get(0).getLatitude(), "53.332165");
        assertNotNull(ieAddress.get(0).getLongitude());
        assertEquals(ieAddress.get(0).getLongitude(), "-6.256094");

    }

    /**
     * Method: getAddressByPosition(String latitude, String longitude, String range)
     */
    @Test public void testGetAddressByPosition() throws Exception {
        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/rgeo/ie/53.332165/-6.256094?distance=50&format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"summaryline\": \"Irish Pension Trust, 25-28 Marsh House, Adelaide Road, Dublin 2, D02 RY98\",\n" +
                        "        \"organisation\": \"Irish Pension Trust\",\n" +
                        "        \"buildingname\": \"Marsh House\",\n" +
                        "        \"number\": \"25-28\",\n" +
                        "        \"premise\": \"Marsh House, 25-28\",\n" +
                        "        \"street\": \"Adelaide Road\",\n" +
                        "        \"posttown\": \"Dublin 2\",\n" +
                        "        \"county\": \"Dublin\",\n" +
                        "        \"postcode\": \"D02 RY98\",\n" +
                        "        \"latitude\": \"53.332165\",\n" +
                        "        \"longitude\": \"-6.256094\",\n" +
                        "        \"what3words\": \"dunes.chart.handle\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));

        List<Address> ieAddress = addressService.getAddressByPosition("53.332165", "-6.256094", "50");

        assertThat(ieAddress, is(not(empty())));
        assertNotNull(ieAddress.get(0).getLatitude());
        assertEquals(ieAddress.get(0).getLatitude(), "53.332165");
        assertNotNull(ieAddress.get(0).getLongitude());
        assertEquals(ieAddress.get(0).getLongitude(), "-6.256094");

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

} 
