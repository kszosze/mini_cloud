package com.microf.backend.service;

import com.microf.model.Address;
import com.microf.model.Position;

import java.util.List;

public interface IAddressService {
    List<Address> getAddressByCode(String search_param);

    List<Address> getAddressGeoByCode(String search_param);

    List<Address> getAddressByCodeAndWhat3Words(String search_param);

    List<Position> getAddressPositionByCode(String search_param);

    List<Address> getAddressByPosition(String latitude, String longitude, String range);
}
