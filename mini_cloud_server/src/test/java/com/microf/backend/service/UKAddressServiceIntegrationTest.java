package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.microf.backend.ServerCommonConfigurationTest;
import com.microf.backend.ServerConfigurationIntegrationTest;
import com.microf.backend.model.Address;
import com.microf.backend.model.UKAddress;
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

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServerCommonConfigurationTest.class, ServerConfigurationIntegrationTest.class})
public class UKAddressServiceIntegrationTest {

    private final Logger log = LoggerFactory.getLogger(UKAddressServiceIntegrationTest.class);

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("UKAddressService")
    private IAddressService addressService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getAddressByCode(String search_param)
     */
    @Test
    public void testGetAddressByCode() throws Exception {

        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/address/uk/NR147ZZ?format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"addressline1\": \"Allies Computing Ltd\",\n" +
                        "        \"addressline2\": \"Manor Farm Barns, Fox Road\",\n" +
                        "        \"addressline3\": \"Framingham Pigot\",\n" +
                        "        \"summaryline\": \"Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\n" +
                        "        \"organisation\": \"Allies Computing Ltd\",\n" +
                        "        \"buildingname\": \"Manor Farm Barns\",\n" +
                        "        \"premise\": \"Manor Farm Barns\",\n" +
                        "        \"street\": \"Fox Road\",\n" +
                        "        \"dependentlocality\": \"Framingham Pigot\",\n" +
                        "        \"posttown\": \"Norwich\",\n" +
                        "        \"county\": \"Norfolk\",\n" +
                        "        \"postcode\": \"NR14 7PZ\",\n" +
                        "        \"latitude\": \"52.5859889436\",\n" +
                        "        \"longitude\": \"1.3492353929\",\n" +
                        "        \"grideasting\": \"626981\",\n" +
                        "        \"gridnorthing\": \"303953\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));

        List<Address> ukAddress = addressService.getAddressByCode("NR147ZZ");

        assertThat(ukAddress, is(not(empty())));

        assertTrue(ukAddress.get(0) instanceof UKAddress);

    }


    /**
     * Method: getAddressGeoByCode(String search_param)
     */
    @Test
    public void testGetAddressGeoByCode() throws Exception {
        mockServer
                .expect(requestTo("http://ws.postcoder.com/pcw/PCW2V-8ZZRQ-Y8P5R-TTPKE/addressgeo/uk/NR147ZZ?format=json&addtags=w3w"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[\n" +
                        "    {\n" +
                        "        \"addressline1\": \"Allies Computing Ltd\",\n" +
                        "        \"addressline2\": \"Manor Farm Barns, Fox Road\",\n" +
                        "        \"addressline3\": \"Framingham Pigot\",\n" +
                        "        \"summaryline\": \"Allies Computing Ltd, Manor Farm Barns, Fox Road, Framingham Pigot, Norwich, Norfolk, NR14 7PZ\",\n" +
                        "        \"organisation\": \"Allies Computing Ltd\",\n" +
                        "        \"buildingname\": \"Manor Farm Barns\",\n" +
                        "        \"premise\": \"Manor Farm Barns\",\n" +
                        "        \"street\": \"Fox Road\",\n" +
                        "        \"dependentlocality\": \"Framingham Pigot\",\n" +
                        "        \"posttown\": \"Norwich\",\n" +
                        "        \"county\": \"Norfolk\",\n" +
                        "        \"postcode\": \"NR14 7PZ\",\n" +
                        "        \"latitude\": \"52.5859889436\",\n" +
                        "        \"longitude\": \"1.3492353929\",\n" +
                        "        \"grideasting\": \"626981\",\n" +
                        "        \"gridnorthing\": \"303953\"\n" +
                        "    }]", MediaType.APPLICATION_JSON));

        List<Address> ukAddress = addressService.getAddressGeoByCode("NR147ZZ");

        assertThat(ukAddress, is(not(empty())));
    }

} 
