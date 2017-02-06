package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.microf.model.Address;
import com.microf.model.Position;
import com.microf.model.UKAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.microf.backend.service.ServiceConstants.ADDRESS_MAP_UK;

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
        final IMap<String,Address> map = hazelcastnstance.getMap(ADDRESS_MAP_UK);

        final Predicate postCodePredicate = Predicates.equal( "postcode", search_param );
        listAddress.addAll(map.values(postCodePredicate));

        if (listAddress.isEmpty()) {
            uri_map.put("search_param", search_param);
            ResponseEntity<UKAddress[]> ukAddress = restTemplate
                    .getForEntity("http://ws.postcoder.com/pcw/{api-key}/address/uk/{search_param}?format=json", UKAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ukAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
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
