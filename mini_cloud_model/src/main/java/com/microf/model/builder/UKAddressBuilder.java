package com.microf.model.builder;

import com.microf.model.UKAddress;

public class UKAddressBuilder {
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
    private String pobox;
    private String departmentname;
    private String subbuildingname;
    private String recodes;
    private String morevalues;
    private String nextpage;
    private String totalresults;
    private String alias;

    public UKAddressBuilder setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public UKAddressBuilder setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public UKAddressBuilder setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
        return this;
    }

    public UKAddressBuilder setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
        return this;
    }

    public UKAddressBuilder setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
        return this;
    }

    public UKAddressBuilder setSummaryline(String summaryline) {
        this.summaryline = summaryline;
        return this;
    }

    public UKAddressBuilder setBuildingname(String buildingname) {
        this.buildingname = buildingname;
        return this;
    }

    public UKAddressBuilder setOrganisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public UKAddressBuilder setNumber(String number) {
        this.number = number;
        return this;
    }

    public UKAddressBuilder setPremise(String premise) {
        this.premise = premise;
        return this;
    }

    public UKAddressBuilder setDependentstreet(String dependentstreet) {
        this.dependentstreet = dependentstreet;
        return this;
    }

    public UKAddressBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public UKAddressBuilder setDoubledependentlocality(String doubledependentlocality) {
        this.doubledependentlocality = doubledependentlocality;
        return this;
    }

    public UKAddressBuilder setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
        return this;
    }

    public UKAddressBuilder setPosttown(String posttown) {
        this.posttown = posttown;
        return this;
    }

    public UKAddressBuilder setCounty(String county) {
        this.county = county;
        return this;
    }

    public UKAddressBuilder setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public UKAddressBuilder setPobox(String pobox) {
        this.pobox = pobox;
        return this;
    }

    public UKAddressBuilder setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
        return this;
    }

    public UKAddressBuilder setSubbuildingname(String subbuildingname) {
        this.subbuildingname = subbuildingname;
        return this;
    }

    public UKAddressBuilder setRecodes(String recodes) {
        this.recodes = recodes;
        return this;
    }

    public UKAddressBuilder setMorevalues(String morevalues) {
        this.morevalues = morevalues;
        return this;
    }

    public UKAddressBuilder setNextpage(String nextpage) {
        this.nextpage = nextpage;
        return this;
    }

    public UKAddressBuilder setTotalresults(String totalresults) {
        this.totalresults = totalresults;
        return this;
    }

    public UKAddressBuilder setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public UKAddress createUKAddress() {
        return new UKAddress(latitude, longitude, addressline1, addressline2, addressline3, summaryline, buildingname, organisation, number, premise, dependentstreet, street, doubledependentlocality, dependentlocality, posttown, county, postcode, pobox, departmentname, subbuildingname, recodes, morevalues, nextpage, totalresults, alias);
    }
}