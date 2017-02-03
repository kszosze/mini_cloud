package com.microf.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microf.model.IEAddress;

@RestController
@RequestMapping("/address")
public class AddressController {

	@RequestMapping("address/ie/{iecode}")
	public IEAddress getAddressByCode() {
		return new IEAddress();
	}
}
