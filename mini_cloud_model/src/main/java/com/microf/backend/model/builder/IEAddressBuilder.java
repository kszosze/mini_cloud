package com.microf.backend.model.builder;

import com.microf.backend.model.IEAddress;

public class IEAddressBuilder {
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
    private String what3words;

    public IEAddressBuilder setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public IEAddressBuilder setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public IEAddressBuilder setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
        return this;
    }

    public IEAddressBuilder setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
        return this;
    }

    public IEAddressBuilder setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
        return this;
    }

    public IEAddressBuilder setSummaryline(String summaryline) {
        this.summaryline = summaryline;
        return this;
    }

    public IEAddressBuilder setBuildingname(String buildingname) {
        this.buildingname = buildingname;
        return this;
    }

    public IEAddressBuilder setOrganisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public IEAddressBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public IEAddressBuilder setPremise(String premise) {
        this.premise = premise;
        return this;
    }

    public IEAddressBuilder setDependentstreet(String dependentstreet) {
        this.dependentstreet = dependentstreet;
        return this;
    }

    public IEAddressBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public IEAddressBuilder setDoubledependentlocality(String doubledependentlocality) {
        this.doubledependentlocality = doubledependentlocality;
        return this;
    }

    public IEAddressBuilder setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
        return this;
    }

    public IEAddressBuilder setPosttown(String posttown) {
        this.posttown = posttown;
        return this;
    }

    public IEAddressBuilder setCounty(String county) {
        this.county = county;
        return this;
    }

    public IEAddressBuilder setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public IEAddressBuilder setWhat3words(String what3words) {
        this.what3words = what3words;
        return this;
    }

    public IEAddress createIEAddress() {
        return new IEAddress(latitude, longitude, addressline1, addressline2, addressline3, summaryline, buildingname, organisation, number, premise, dependentstreet, street, doubledependentlocality, dependentlocality, posttown, county, postcode, what3words);
    }
}