package com.austine.fms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    @Id
    @Column(name = "AIRPORT_CODE")
    private String airportCode;

    @Column(name = "AIRPORT_LOCATION")
    private String airportLocation;

    @Column(name = "AIRPORT_NAME")
    private String airportName;

    public Airport() {
    }

    public Airport(String airportCode, String airportLocation, String airportName) {
        this.airportCode = airportCode;
        this.airportLocation = airportLocation;
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportLocation() {
        return airportLocation;
    }

    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (airportCode != null ? !airportCode.equals(airport.airportCode) : airport.airportCode != null) return false;
        if (airportLocation != null ? !airportLocation.equals(airport.airportLocation) : airport.airportLocation != null)
            return false;
        return airportName != null ? airportName.equals(airport.airportName) : airport.airportName == null;
    }

    @Override
    public int hashCode() {
        int result = airportCode != null ? airportCode.hashCode() : 0;
        result = 31 * result + (airportLocation != null ? airportLocation.hashCode() : 0);
        result = 31 * result + (airportName != null ? airportName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", airportLocation='" + airportLocation + '\'' +
                ", airportName='" + airportName + '\'' +
                '}';
    }
}
