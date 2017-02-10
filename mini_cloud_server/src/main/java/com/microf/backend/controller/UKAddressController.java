package com.microf.backend.controller;

import com.microf.backend.service.IAddressService;
import com.microf.backend.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address/uk")
public class UKAddressController {

    @Autowired
	@Qualifier("UKAddressService")
    private IAddressService addressService;

    @Value("search_range")
    private String SEARCH_RANGE;

	@RequestMapping("/{ukCode}")
	public List<Address> getIEAddressByCode(@PathVariable("ukCode") String ierCode) {
		return addressService.getAddressByCode(ierCode);
	}

	@RequestMapping("/addressgeo/{ukCode}")
	public List<Address> getAddressAndGeoByCode(@PathVariable("ukCode") String ierCode) {
		return addressService.getAddressGeoByCode(ierCode);
	}

}
