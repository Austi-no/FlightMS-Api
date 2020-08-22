package com.austine.fms.service;

import com.austine.fms.exceptions.RecordNotFoundException;
import com.austine.fms.exceptions.ScheduledFlightNotFoundException;
import com.austine.fms.model.Schedule;
import com.austine.fms.model.ScheduledFlight;
import com.austine.fms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class ApiService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ScheduledFlightRepository scheduledFlightRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;



    public AirportRepository getAirportRepository() {
        return airportRepository;
    }

    public void setAirportRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduleFlight) {
        ScheduledFlight updateScheduleFlight = scheduledFlightRepository.findById(scheduleFlight.getScheduleFlightId()).get();
        Schedule updateSchedule = scheduleRepository.findById(scheduleFlight.getSchedule().getScheduleId()).get();
        updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
        updateSchedule.setSrcAirport(scheduleFlight.getSchedule().getSrcAirport());
        updateSchedule.setDstnAirport(scheduleFlight.getSchedule().getDstnAirport());

        updateSchedule.setArrivalTime(scheduleFlight.getSchedule().getArrivalTime(new Date()));
        updateSchedule.setDepartureTime(scheduleFlight.getSchedule().getDepartureTime(new Date()));
        scheduledFlightRepository.save(updateScheduleFlight);
        return scheduleFlight;
    }

    public String removeScheduledFlight(Long flightId) throws RecordNotFoundException {
        if (flightId == null)
            throw new RecordNotFoundException("Enter flight Id");
        Optional<ScheduledFlight> scheduleFlight = scheduledFlightRepository.findById(flightId);
        if (!scheduleFlight.isPresent())
            throw new RecordNotFoundException("Enter a valid Flight Id");
        else {
            scheduledFlightRepository.deleteById(flightId);
        }
        return "Scheduled flight with ID " + flightId + " is not found";
    }

    public ScheduledFlight viewScheduledFlight(Long flightId) throws ScheduledFlightNotFoundException {
        if (flightId == null)
            throw new ScheduledFlightNotFoundException("Enter flight Id");
        Optional<ScheduledFlight> scheduleFlight = scheduledFlightRepository.findById(flightId);
        if (!scheduleFlight.isPresent())
            throw new ScheduledFlightNotFoundException("Enter a valid Flight Id");
        else
            return scheduleFlight.get();
    }

    public BookingRepository getBookingRepsitory() {
        return bookingRepository;
    }

    public void setBookingRepsitory(BookingRepository bookingRepsitory) {
        this.bookingRepository = bookingRepsitory;
    }

    public FlightRepository getFlightRepository() {
        return flightRepository;
    }

    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public PassengerRepository getPassengerRepository() {
        return passengerRepository;
    }

    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public ScheduledFlightRepository getScheduledFlightRepository() {
        return scheduledFlightRepository;
    }

    public void setScheduledFlightRepository(ScheduledFlightRepository scheduledFlightRepository) {
        this.scheduledFlightRepository = scheduledFlightRepository;
    }

    public ScheduleRepository getScheduleRepository() {
        return scheduleRepository;
    }

    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
