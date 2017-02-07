package com.microf.model.builder;

import com.microf.model.Address;

public class AddressBuilder {
    private String latitude;
    private String longitude;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String summaryline;
    private String buildingname;
    private String organisation;
    private String number;
    private String premise;
    private String dependentstreet;
    private String street;
    private String doubledependentlocality;
    private String dependentlocality;
    private String posttown;
    private String county;
    private String postcode;

    public AddressBuilder setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public AddressBuilder setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public AddressBuilder setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
        return this;
    }

    public AddressBuilder setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
        return this;
    }

    public AddressBuilder setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
        return this;
    }

    public AddressBuilder setSummaryline(String summaryline) {
        this.summaryline = summaryline;
        return this;
    }

    public AddressBuilder setBuildingname(String buildingname) {
        this.buildingname = buildingname;
        return this;
    }

    public AddressBuilder setOrganisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public AddressBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressBuilder setPremise(String premise) {
        this.premise = premise;
        return this;
    }

    public AddressBuilder setDependentstreet(String dependentstreet) {
        this.dependentstreet = dependentstreet;
        return this;
    }

    public AddressBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder setDoubledependentlocality(String doubledependentlocality) {
        this.doubledependentlocality = doubledependentlocality;
        return this;
    }

    public AddressBuilder setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
        return this;
    }

    public AddressBuilder setPosttown(String posttown) {
        this.posttown = posttown;
        return this;
    }

    public AddressBuilder setCounty(String county) {
        this.county = county;
        return this;
    }

    public AddressBuilder setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Address createAddress() {
        return new Address(latitude, longitude, addressline1, addressline2, addressline3, summaryline, buildingname, organisation, number, premise, dependentstreet, street, doubledependentlocality, dependentlocality, posttown, county, postcode);
    }
}