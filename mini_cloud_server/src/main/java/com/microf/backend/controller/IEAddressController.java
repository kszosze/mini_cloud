package com.microf.backend.controller;

import com.microf.backend.service.IAddressService;
import com.microf.model.IEAddress;
import com.microf.model.Position;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address/ie")
public class IEAddressController {

    @Autowired
    @Qualifier("IEAddressService")
    private IAddressService addressService;

    @Value("search_range")
    private String SEARCH_RANGE;

	@RequestMapping("/{ierCode}")
	public List<IEAddress> getIEAddressByCode(@PathVariable("ierCode") String ierCode) {
		return addressService.getAddressByCode(ierCode);
	}

	@RequestMapping("/addressgeo/{ierCode}")
	public List<IEAddress> getAddressAndGeoByCode(@PathVariable("ierCode") String ierCode,
                                                  @RequestParam("addtags") String addTags) {
	    final List<IEAddress> listAddress = new ArrayList<>();
	    if (StringUtils.isEmpty(addTags)) {
            listAddress.addAll(addressService.getAddressGeoByCode(ierCode));
        } else {
            listAddress.addAll(addressService.getAddressByCodeAndWhat3Words(ierCode));
        }
        return listAddress;
	}

	@RequestMapping("/position/{ierCode}")
    public Position getIEPosition(@PathVariable("ierCode") String ierCode) {
	    return addressService.getAddressPositionByCode(ierCode).get(0);
	}

    @RequestMapping("/rgeo/53.332067/-6.255492")
    public List<IEAddress> getIEAddressFromPosition(@PathVariable("latitude") String latitude,
                                              @PathVariable("longitude") String longitude) {
	    return addressService.getAddressByPosition(latitude, longitude, SEARCH_RANGE);
	}

}
