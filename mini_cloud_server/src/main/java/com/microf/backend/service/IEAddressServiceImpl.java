package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.microf.model.Address;
import com.microf.model.IEAddress;
import com.microf.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.microf.backend.service.ServiceConstants.*;

@Service("IEAddressService")
public class IEAddressServiceImpl implements IAddressService {


    @Autowired
    private HazelcastInstance instance;

    @Value("server.api.key")
    private String api_key;

    @Value("server.api.address.by.code.ie")
    private String addressByCodeURL;

    @Value("server.api.addressgeo.ie")
    private String addressgeoByCodeURL;

    @Value("server.api.address.by.position.ie")
    private String addressByPositionURL;

    private final Map<String, String> uri_map = new HashMap<>();

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    private void init() {
        uri_map.put(API_KEY, api_key);
    }

    @Override
    public List<Address> getAddressByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,IEAddress> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        final Predicate addressPredicate = Predicates.ilike(SUMMARYLINE, search_param );
        final Predicate predicate = Predicates.or( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));

        if (listAddress.isEmpty()) {
            uri_map.put(SEARCH_PARAM, search_param);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity(addressByCodeURL, IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }

    @Override
    public List<Address> getAddressGeoByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,Address> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        listAddress.addAll(map.values(postCodePredicate));

        if (listAddress.isEmpty()) {
            uri_map.put(SEARCH_PARAM, search_param);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity(addressgeoByCodeURL, IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }


    @Override
    public List<Address> getAddressByCodeAndWhat3Words(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,IEAddress> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        final Predicate addressPredicate = Predicates.ilike(SUMMARYLINE, search_param );
        final Predicate predicate = Predicates.or( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));

        if (listAddress.isEmpty()) {
            uri_map.put(SEARCH_PARAM, search_param);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity(addressgeoByCodeURL.concat("&addtags=w3w"), IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }

    @Override
    public List<Position> getAddressPositionByCode(String search_param) {
        final List<Address> listAddress = getAddressGeoByCode(search_param);
        final List<Position> listPosition = new ArrayList<>();
        listAddress.forEach(address -> {
            listPosition.add(new Position(address.getLatitude(), address.getLongitude()));
        });
        return listPosition;
    }

    @Override
    public List<Address> getAddressByPosition(String latitude, String longitude, String range) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,IEAddress> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(LATITUDE, latitude );
        final Predicate addressPredicate = Predicates.ilike(LONGITUDE, longitude );

        final Predicate predicate = Predicates.and( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));
        if (listAddress.isEmpty()) {
            uri_map.put(LATITUDE, latitude);
            uri_map.put(LONGITUDE, longitude);
            uri_map.put(RANGE, range);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity(addressByPositionURL, IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }

}
