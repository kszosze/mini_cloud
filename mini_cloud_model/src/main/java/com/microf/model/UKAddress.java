package com.microf.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UKAddress extends Address {

    private String pobox; // 	String 	PO Box
    private String departmentname; // 	String 	Department name
    private String subbuildingname; // 	String 	Sub Building name
    private String recodes; // 	String 	Colon separated list of the previous postcode, previous delivery point suffix and the date it changed (YYYYMM format)
    private String morevalues; // 	Boolean 	Indicates at least one more page of addresses for the search
    private String nextpage; // 	Integer 	Page number for the next page of results
    private String totalresults; // 	Integer 	Total number of addresses for the search (only shown if more than 100 results)
    private String alias; // 	Boolean 	Indicates address is an alias record, only present when used with ?alias=true

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getSubbuildingname() {
        return subbuildingname;
    }

    public void setSubbuildingname(String subbuildingname) {
        this.subbuildingname = subbuildingname;
    }

    public String getRecodes() {
        return recodes;
    }

    public void setRecodes(String recodes) {
        this.recodes = recodes;
    }

    public String getMorevalues() {
        return morevalues;
    }

    public void setMorevalues(String morevalues) {
        this.morevalues = morevalues;
    }

    public String getNextpage() {
        return nextpage;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    public String getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(String totalresults) {
        this.totalresults = totalresults;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UKAddress)) return false;

        UKAddress ukAddress = (UKAddress) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getPobox(), ukAddress.getPobox())
                .append(getDepartmentname(), ukAddress.getDepartmentname())
                .append(getSubbuildingname(), ukAddress.getSubbuildingname())
                .append(getRecodes(), ukAddress.getRecodes())
                .append(getMorevalues(), ukAddress.getMorevalues())
                .append(getNextpage(), ukAddress.getNextpage())
                .append(getTotalresults(), ukAddress.getTotalresults())
                .append(getAlias(), ukAddress.getAlias())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getPobox())
                .append(getDepartmentname())
                .append(getSubbuildingname())
                .append(getRecodes())
                .append(getMorevalues())
                .append(getNextpage())
                .append(getTotalresults())
                .append(getAlias())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pobox", pobox)
                .append("departmentname", departmentname)
                .append("subbuildingname", subbuildingname)
                .append("recodes", recodes)
                .append("morevalues", morevalues)
                .append("nextpage", nextpage)
                .append("totalresults", totalresults)
                .append("alias", alias)
                .toString();
    }
}
