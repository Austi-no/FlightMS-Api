package com.austine.fms.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "scheduled_Flight")
public class ScheduledFlight implements Serializable {

    @Id
    @Column(name = "SHEDULED_FLIGHT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleFlightId;

    @Column(name = "AVAILABLE_SEATS")
    private Long availableSeats;
    @JoinColumn(name = "SCHEDULE")
    @OneToOne(cascade = CascadeType.ALL)
    private Schedule schedule;

    @JoinColumn(name = "FLIGHT")
    @OneToOne(fetch = FetchType.EAGER)
    private Flight flight;

    public ScheduledFlight() {
    }

    public Long getScheduleFlightId() {
        return scheduleFlightId;
    }

    public void setScheduleFlightId(Long scheduleFlightId) {
        this.scheduleFlightId = scheduleFlightId;
    }

    public Long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Long availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledFlight that = (ScheduledFlight) o;

        if (scheduleFlightId != null ? !scheduleFlightId.equals(that.scheduleFlightId) : that.scheduleFlightId != null)
            return false;
        if (availableSeats != null ? !availableSeats.equals(that.availableSeats) : that.availableSeats != null)
            return false;
        if (schedule != null ? !schedule.equals(that.schedule) : that.schedule != null) return false;
        return flight != null ? flight.equals(that.flight) : that.flight == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleFlightId != null ? scheduleFlightId.hashCode() : 0;
        result = 31 * result + (availableSeats != null ? availableSeats.hashCode() : 0);
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        result = 31 * result + (flight != null ? flight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScheduledFlight{" +
                "scheduleFlightId=" + scheduleFlightId +
                ", availableSeats=" + availableSeats +
                ", schedule=" + schedule +
                ", flight=" + flight +
                '}';
    }
}
