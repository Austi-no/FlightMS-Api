package com.austine.fms.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="passenger")
public class Passenger implements Serializable {

    @Id
    @Column(name = "PASSENGER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passId;

    @Column(name = "PASSENGER_NAME")
    private String passName;

    @Column(name = "PASSENGER_AGE")
    private Long passAge;

    @Column(name = "PASSENGER_UIN")
    private Long passUIN;

    @Column(name = "LUGGAGE")
    private Long luggage;

    public Passenger() {
    }

    public Long getPassId() {
        return passId;
    }

    public void setPassId(Long passId) {
        this.passId = passId;
    }

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName;
    }

    public Long getPassAge() {
        return passAge;
    }

    public void setPassAge(Long passAge) {
        this.passAge = passAge;
    }

    public Long getPassUIN() {
        return passUIN;
    }

    public void setPassUIN(Long passUIN) {
        this.passUIN = passUIN;
    }

    public Long getLuggage() {
        return luggage;
    }

    public void setLuggage(Long luggage) {
        this.luggage = luggage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (passId != null ? !passId.equals(passenger.passId) : passenger.passId != null) return false;
        if (passName != null ? !passName.equals(passenger.passName) : passenger.passName != null) return false;
        if (passAge != null ? !passAge.equals(passenger.passAge) : passenger.passAge != null) return false;
        if (passUIN != null ? !passUIN.equals(passenger.passUIN) : passenger.passUIN != null) return false;
        return luggage != null ? luggage.equals(passenger.luggage) : passenger.luggage == null;
    }

    @Override
    public int hashCode() {
        int result = passId != null ? passId.hashCode() : 0;
        result = 31 * result + (passName != null ? passName.hashCode() : 0);
        result = 31 * result + (passAge != null ? passAge.hashCode() : 0);
        result = 31 * result + (passUIN != null ? passUIN.hashCode() : 0);
        result = 31 * result + (luggage != null ? luggage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passId=" + passId +
                ", passName='" + passName + '\'' +
                ", passAge=" + passAge +
                ", passUIN=" + passUIN +
                ", luggage=" + luggage +
                '}';
    }

}
