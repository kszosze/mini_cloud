package com.microf.backend.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.microf.backend.model.Address;
import com.microf.backend.model.IEAddress;
import com.microf.backend.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.microf.backend.service.ServiceConstants.*;

@Service(value = "IEAddressService")
@Qualifier( "IEAddressService")
public class IEAddressServiceImpl implements IAddressService {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Value("${server.api.key}")
    private String api_key;

    @Value("${server.api.address.by.code.ie}")
    private String addressByCodeURL;

    @Value("${server.api.addressgeo.ie}")
    private String addressgeoByCodeURL;

    @Value("${server.api.address.by.position.ie}")
    private String addressByPositionURL;

    private final Map<String, String> uri_map = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    public IEAddressServiceImpl(final RestTemplate restTemplate, final HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
        this.restTemplate = restTemplate;
    }

    public IEAddressServiceImpl() {
    }

    @PostConstruct
    private void init() {
        uri_map.put(API_KEY, api_key);
    }

    @Override
    public List<Address> getAddressByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<Integer,IEAddress> map = hazelcastInstance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        final Predicate addressPredicate = Predicates.ilike(SUMMARYLINE, search_param );
        final Predicate predicate = Predicates.or( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));

        if (listAddress.isEmpty()) {
            listAddress.addAll(getAddressByCodeAndWhat3Words(search_param));
        }
        return listAddress;
    }

    @Override
    public List<Address> getAddressGeoByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<Integer,IEAddress> map = hazelcastInstance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        listAddress.addAll(map.values(postCodePredicate));

        if (listAddress.isEmpty()) {
            listAddress.addAll(getAddressByCodeAndWhat3Words(search_param));
        }
        return listAddress;
    }


    @Override
    public List<Address> getAddressByCodeAndWhat3Words(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<Integer,IEAddress> map = hazelcastInstance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(POSTCODE, search_param );
        final Predicate addressPredicate = Predicates.ilike(SUMMARYLINE, search_param );
        final Predicate predicate = Predicates.or( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));

        if (listAddress.isEmpty()) {
            uri_map.put(SEARCH_PARAM, search_param);
            fillCollections(restTemplate
                    .getForEntity(addressgeoByCodeURL, IEAddress[].class, uri_map), map, listAddress);

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
        final IMap<Integer,IEAddress> map = hazelcastInstance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal(LATITUDE, latitude );
        final Predicate addressPredicate = Predicates.ilike(LONGITUDE, longitude );

        final Predicate predicate = Predicates.and( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));
        if (listAddress.isEmpty()) {
            uri_map.put(LATITUDE, latitude);
            uri_map.put(LONGITUDE, longitude);
            uri_map.put(RANGE, range);
            fillCollections(restTemplate
                    .getForEntity(addressByPositionURL, IEAddress[].class, uri_map), map, listAddress);

        }
        return listAddress;
    }

    private void fillCollections(final ResponseEntity<IEAddress[]> ieAddressJSON,
                                 final IMap<Integer,IEAddress> map,
                                 final List<Address> listAddress) {

        for (IEAddress ieAddress : ieAddressJSON.getBody()) {
            map.put(ieAddress.hashCode(), ieAddress);
            listAddress.add(ieAddress);
        }
    }
}
