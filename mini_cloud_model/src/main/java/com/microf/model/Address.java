package com.microf.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address extends Position {

    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String summaryline;//	Summary of the address
    private String buildingname; // 	String 	Building name 	Manor Farm Barns
    private String organisation; // 	String 	Organisation 	Allies Computing Ltd
    private String number;//	Number
    private String premise; // 	String 	Composite of all premise level elements including department, building name, sub building name, number and PO Box where applicable. 	Manor Farm Barns
    private String dependentstreet; // 	String 	Dependent street
    private String street;	//Street
    private String doubledependentlocality; // 	String 	Double dependent locality
    private String dependentlocality; // 	String 	Dependent locality 	Framingham Pigot
    private String posttown; // 	String 	Post town 	Norwich
    private String county; // 	String 	County 	Norfolk
    private String postcode; // 	String 	Postcode 	NR14 7PZ

    public Address(String latitude, String longitude) {
        super(latitude, longitude);
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public String getSummaryline() {
        return summaryline;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getDependentstreet() {
        return dependentstreet;
    }

    public void setDependentstreet(String dependentstreet) {
        this.dependentstreet = dependentstreet;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDoubledependentlocality() {
        return doubledependentlocality;
    }

    public void setDoubledependentlocality(String doubledependentlocality) {
        this.doubledependentlocality = doubledependentlocality;
    }

    public String getDependentlocality() {
        return dependentlocality;
    }

    public void setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
    }

    public String getPosttown() {
        return posttown;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Address)) return false;

        Address that = (Address) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getAddressline1(), that.getAddressline1())
                .append(getAddressline2(), that.getAddressline2())
                .append(getAddressline3(), that.getAddressline3())
                .append(getSummaryline(), that.getSummaryline())
                .append(getBuildingname(), that.getBuildingname())
                .append(getOrganisation(), that.getOrganisation())
                .append(getNumber(), that.getNumber())
                .append(getPremise(), that.getPremise())
                .append(getDependentstreet(), that.getDependentstreet())
                .append(getStreet(), that.getStreet())
                .append(getDoubledependentlocality(), that.getDoubledependentlocality())
                .append(getDependentlocality(), that.getDependentlocality())
                .append(getPosttown(), that.getPosttown())
                .append(getCounty(), that.getCounty())
                .append(getPostcode(), that.getPostcode())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getAddressline1())
                .append(getAddressline2())
                .append(getAddressline3())
                .append(getSummaryline())
                .append(getBuildingname())
                .append(getOrganisation())
                .append(getNumber())
                .append(getPremise())
                .append(getDependentstreet())
                .append(getStreet())
                .append(getDoubledependentlocality())
                .append(getDependentlocality())
                .append(getPosttown())
                .append(getCounty())
                .append(getPostcode())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressline1", addressline1)
                .append("addressline2", addressline2)
                .append("addressline3", addressline3)
                .append("summaryline", summaryline)
                .append("buildingname", buildingname)
                .append("organisation", organisation)
                .append("number", number)
                .append("premise", premise)
                .append("dependentstreet", dependentstreet)
                .append("street", street)
                .append("doubledependentlocality", doubledependentlocality)
                .append("dependentlocality", dependentlocality)
                .append("posttown", posttown)
                .append("county", county)
                .append("postcode", postcode)
                .toString();
    }
}
