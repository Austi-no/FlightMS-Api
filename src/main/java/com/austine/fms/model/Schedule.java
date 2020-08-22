package com.austine.fms.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable{

    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(name = "ARRIVAL_ID")
    private Date arrivalTime;

    @Column(name = "DEPARTURE_TIME")
    private Date departureTime;


    @JoinColumn(name = "SOURCE_AIRPORT")
    @OneToOne(fetch = FetchType.EAGER)
    private Airport srcAirport;

    @JoinColumn(name = "DESTINATION_AIRPORT")
    @OneToOne(fetch = FetchType.EAGER)
    private Airport dstnAirport;

    public Schedule() {
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getArrivalTime(Date arrivalTime) {
        return this.arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime(Date departureTime) {
        return this.departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Airport getSrcAirport() {
        return srcAirport;
    }

    public void setSrcAirport(Airport srcAirport) {
        this.srcAirport = srcAirport;
    }

    public Airport getDstnAirport() {
        return dstnAirport;
    }

    public void setDstnAirport(Airport dstnAirport) {
        this.dstnAirport = dstnAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (scheduleId != null ? !scheduleId.equals(schedule.scheduleId) : schedule.scheduleId != null) return false;
        if (arrivalTime != null ? !arrivalTime.equals(schedule.arrivalTime) : schedule.arrivalTime != null)
            return false;
        if (departureTime != null ? !departureTime.equals(schedule.departureTime) : schedule.departureTime != null)
            return false;
        if (srcAirport != null ? !srcAirport.equals(schedule.srcAirport) : schedule.srcAirport != null) return false;
        return dstnAirport != null ? dstnAirport.equals(schedule.dstnAirport) : schedule.dstnAirport == null;
    }

    @Override
    public int hashCode() {
        int result = scheduleId != null ? scheduleId.hashCode() : 0;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + (srcAirport != null ? srcAirport.hashCode() : 0);
        result = 31 * result + (dstnAirport != null ? dstnAirport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", srcAirport=" + srcAirport +
                ", dstnAirport=" + dstnAirport +
                '}';
    }
}
