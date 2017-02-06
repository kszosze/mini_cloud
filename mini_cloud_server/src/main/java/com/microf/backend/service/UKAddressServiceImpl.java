package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.microf.model.Address;
import com.microf.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UKAddressServiceImpl implements IAddressService {

    @Autowired
    private HazelcastInstance hazelcastnstance;

    @Value("API_KEY")
    private String api_key;

    private final Map<String, String> uri_map = new HashMap<>();

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init() {
        uri_map.put("api_key", api_key);
    }

    @Override
    public List<Address> getAddressByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();

        return listAddress;
    }

    @Override
    public List<Address> getAddressGeoByCode(String search_param) {
        return null;
    }

    @Override
    public List<Address> getAddressByCodeAndWhat3Words(String search_param) {
        return null;
    }

    @Override
    public List<Position> getAddressPositionByCode(String search_param) {
        return null;
    }

    @Override
    public List<Address> getAddressByPosition(String latitude, String longitude, String range) {
        return null;
    }
}
