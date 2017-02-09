package com.microf.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IEAddress extends Address {

    private String what3words;

    public IEAddress(){};

    public IEAddress(String latitude, String longitude, String addressline1, String addressline2, String addressline3, String summaryline, String buildingname, String organisation, String number, String premise, String dependentstreet, String street, String doubledependentlocality, String dependentlocality, String posttown, String county, String postcode, String what3words) {
        super(latitude, longitude, addressline1, addressline2, addressline3, summaryline, buildingname, organisation, number, premise, dependentstreet, street, doubledependentlocality, dependentlocality, posttown, county, postcode);
        this.what3words = what3words;
    }

    public IEAddress(final String latitude, final String longitude, final String what3words) {
        super(latitude, longitude);
        this.what3words = what3words;
    }

    public String getWhat3words() {
        return what3words;
    }

    public void setWhat3words(String what3words) {
        this.what3words = what3words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof IEAddress)) return false;

        IEAddress ieAddress = (IEAddress) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getWhat3words(), ieAddress.getWhat3words())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getWhat3words())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("what3words", what3words)
                .toString();
    }
}
