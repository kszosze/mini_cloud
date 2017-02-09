package com.microf.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Position implements Serializable{

    private String latitude; //	ETRS89 Latitude Co-ordinate
    private String longitude; //	ETRS89 Longitude Co-ordinate
    private String grideasting; // Easting co-ordinate
    private String gridnorthing; // Northing co-ordinate

    public Position() {
    }

    public Position(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGrideasting() {
        return grideasting;
    }

    public void setGrideasting(final String grideasting) {
        this.grideasting = grideasting;
    }

    public String getGridnorthing() {
        return gridnorthing;
    }

    public void setGridnorthing(final String gridnorthing) {
        this.gridnorthing = gridnorthing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        return new EqualsBuilder()
                .append(getLatitude(), position.getLatitude())
                .append(getLongitude(), position.getLongitude())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLatitude())
                .append(getLongitude())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString();
    }
}
