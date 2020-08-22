package com.austine.fms.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {

    @Id
    @Column(name = "FLIGHT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @Column(name = "FLIGHT_MODEL")
    private String flightModel;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "SEAT_CAPACITY")
    private Long seatCapacity;

    public Flight() {
    }

    public Long getFlightId() {
        return flightId;
    }


    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Long getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Long seatCapacity) {
        this.seatCapacity = seatCapacity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (flightId != null ? !flightId.equals(flight.flightId) : flight.flightId != null) return false;
        if (flightModel != null ? !flightModel.equals(flight.flightModel) : flight.flightModel != null) return false;
        if (carrierName != null ? !carrierName.equals(flight.carrierName) : flight.carrierName != null) return false;
        return seatCapacity != null ? seatCapacity.equals(flight.seatCapacity) : flight.seatCapacity == null;
    }

    @Override
    public int hashCode() {
        int result = flightId != null ? flightId.hashCode() : 0;
        result = 31 * result + (flightModel != null ? flightModel.hashCode() : 0);
        result = 31 * result + (carrierName != null ? carrierName.hashCode() : 0);
        result = 31 * result + (seatCapacity != null ? seatCapacity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightModel='" + flightModel + '\'' +
                ", carrierName='" + carrierName + '\'' +
                ", seatCapacity=" + seatCapacity +
                '}';
    }
}
