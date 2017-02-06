package com.microf.backend.controller;

import com.microf.backend.service.IAddressService;
import com.microf.model.IEAddress;
import com.microf.model.Position;
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
	public List<IEAddress> getIEAddressByCode(@PathVariable("ukCode") String ierCode) {
		return addressService.getAddressByCode(ierCode);
	}

	@RequestMapping("/addressgeo/{ukCode}")
	public List<IEAddress> getAddressAndGeoByCode(@PathVariable("ukCode") String ierCode) {
		return addressService.getAddressGeoByCode(ierCode);
	}


	@RequestMapping("/position/{ukCode}")
    public Position getIEPosition(@PathVariable("ukCode") String ierCode) { return addressService.getAddressPositionByCode(ierCode).get(0); }

    @RequestMapping("/rgeo/53.332067/-6.255492")
    public List<IEAddress> getIEAddressFromPosition(@PathVariable("latitude") String latitude,
                                              @PathVariable("longitude") String longitude) { return addressService.getAddressByPosition(latitude, longitude, SEARCH_RANGE); }

}
