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

import static com.microf.backend.service.ServiceConstants.ADDRESS_MAP_IE;

@Service("IEAddressService")
public class IEAddressServiceImpl implements IAddressService {

    @Autowired
    private HazelcastInstance instance;

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
        final IMap<String,IEAddress> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal( "postcode", search_param );
        final Predicate addressPredicate = Predicates.ilike( "summaryline", search_param );
        final Predicate predicate = Predicates.or( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));

        if (listAddress.isEmpty()) {
            uri_map.put("search_param", search_param);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity("http://ws.postcoder.com/pcw/{api_key}/address/ie/{search_code}&format=json", IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }

    @Override
    public List<Address> getAddressGeoByCode(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,Address> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal( "postcode", search_param );
        listAddress.addAll(map.values(postCodePredicate));

        if (listAddress.isEmpty()) {
            uri_map.put("search_param", search_param);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity("http://ws.postcoder.com/pcw/{api-key}/addressgeo/ie/{search_param}?format=json", IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }


    @Override
    public List<Address> getAddressByCodeAndWhat3Words(String search_param) {
        final List<Address> listAddress = new ArrayList<>();
        final IMap<String,IEAddress> map = instance.getMap(ADDRESS_MAP_IE);

        final Predicate postCodePredicate = Predicates.equal( "postcode", search_param );
        final Predicate addressPredicate = Predicates.ilike( "summaryline", search_param );
        final Predicate predicate = Predicates.or( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));

        if (listAddress.isEmpty()) {
            uri_map.put("search_param", search_param);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity("http://ws.postcoder.com/pcw/{api_key}/addressgeo/ie/{search_param}?format=json&addtags=w3w", IEAddress[].class, uri_map);
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

        final Predicate postCodePredicate = Predicates.equal( "latitude", latitude );
        final Predicate addressPredicate = Predicates.ilike( "longitude", longitude );

        final Predicate predicate = Predicates.and( postCodePredicate, addressPredicate );
        listAddress.addAll(map.values(predicate));
        if (listAddress.isEmpty()) {
            uri_map.put("latitude", latitude);
            uri_map.put("longitude", longitude);
            uri_map.put("range", range);
            ResponseEntity<IEAddress[]> ieAddress = restTemplate
                    .getForEntity("http://ws.postcoder.com/pcw/{api-key}/rgeo/ie/{latitude}/{longitude}?distance={range}", IEAddress[].class, uri_map);
            listAddress.addAll(Arrays.asList(ieAddress.getBody()));
            listAddress.forEach(address -> map.put(address.getPostcode(), address));
        }
        return listAddress;
    }

}
