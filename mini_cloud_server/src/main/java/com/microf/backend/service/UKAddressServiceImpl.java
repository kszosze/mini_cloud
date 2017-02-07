package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.microf.model.Address;
import com.microf.model.Position;
import com.microf.model.UKAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microf.backend.service.ServiceConstants.*;

@Service(value = "UKAddressService")
@Qualifier("UKAddressService")
public class UKAddressServiceImpl implements IAddressService {

    @Autowired
    private HazelcastInstance hazelcasInstance;

    @Value("API_KEY")
    private String api_key;

    @Value("server.api.address.by.code.uk")
    private String addressByCodeURL;

    @Value("server.api.addressgeo.uk")
    private String addressgeoByCodeURL;

    private final Map<String, String> uri_map = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public UKAddressServiceImpl(final HazelcastInstance hazelcastInstance, final RestTemplate restTemplate) {
        this.hazelcasInstance = hazelcastInstance;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void init() {
        uri_map.put("api_key", api_key);
    }

    @Override
    public List<Address> getAddressByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,UKAddress> map = hazelcasInstance.getMap(ADDRESS_MAP_UK);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        listAddress.addAll(map.values(postCodePredicate));

        if (listAddress.isEmpty()) {
            uri_map.put(SEARCH_PARAM, search_param);
            fillCollections(restTemplate
                    .getForEntity(addressByCodeURL, UKAddress[].class, uri_map), map, listAddress);
        }
        return listAddress;
    }

    @Override
    public List<Address> getAddressGeoByCode(String search_param) {

        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,UKAddress> map = hazelcasInstance.getMap(ADDRESS_MAP_UK);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        listAddress.addAll(map.values(postCodePredicate));

        if (listAddress.isEmpty()) {
            uri_map.put(SEARCH_PARAM, search_param);
            fillCollections(restTemplate
                                .getForEntity(addressgeoByCodeURL, UKAddress[].class, uri_map), map, listAddress);
        }
        return listAddress;

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

    private void fillCollections(final ResponseEntity<UKAddress[]> ukAddressJSON,
                                 final IMap<String,UKAddress> map,
                                 final List<Address> listAddress) {

        for (UKAddress ukAddress : ukAddressJSON.getBody()) {
            map.put(ukAddress.getPostcode(), ukAddress);
            listAddress.add(ukAddress);
        }
    }
}
