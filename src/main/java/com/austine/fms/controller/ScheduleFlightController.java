package com.austine.fms.controller;

import com.austine.fms.exceptions.RecordNotFoundException;
import com.austine.fms.exceptions.ScheduledFlightNotFoundException;
import com.austine.fms.model.Schedule;
import com.austine.fms.model.ScheduledFlight;
import com.austine.fms.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/scheduledFlight")
public class ScheduleFlightController {

    @Autowired
    private ApiService service;

    @ApiOperation("To save a ScheduleFlight details")
    @PostMapping("/add")
    public ResponseEntity<ScheduledFlight> addSF(@ModelAttribute ScheduledFlight scheduledFlight,
                                                 @RequestParam(name = "srcAirport") String source,
                                                 @RequestParam(name = "dstnAirport") String destination,
                                                 @RequestParam(name = "deptDateTime") Date departureTime,
                                                 @RequestParam(name = "arrDateTime") Date arrivalTime) {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduledFlight.getScheduleFlightId());
        try {
            schedule.setSrcAirport(service.getAirportRepository().findById(source).get());
        } catch (RecordNotFoundException e) {
            return new ResponseEntity("Airport Not Found", HttpStatus.NOT_FOUND);
        }
        try {
            schedule.setDstnAirport(service.getAirportRepository().findById(destination).get());
        } catch (RecordNotFoundException e) {
            return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
        }
        schedule.getDepartureTime(departureTime);
        schedule.getArrivalTime(arrivalTime);
        try {
            scheduledFlight.setFlight(service.getFlightRepository().findById(scheduledFlight.getScheduleFlightId()).get());
        } catch (RecordNotFoundException e1) {
            return new ResponseEntity("Flight Not Found", HttpStatus.NOT_FOUND);
        }
        scheduledFlight.setSchedule(schedule);
        scheduledFlight.setAvailableSeats(scheduledFlight.getFlight().getSeatCapacity());
        try {
            return new ResponseEntity<ScheduledFlight>(service.getScheduledFlightRepository().save(scheduledFlight),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error adding Flight." + e, HttpStatus.BAD_REQUEST);
        }
    }
    @ApiOperation("To Update ScheduleFlight details by ID")
    @PutMapping("/modify")
    public ResponseEntity<ScheduledFlight> modifyScheduleFlight(@ModelAttribute ScheduledFlight scheduleFlight) {
        ScheduledFlight modifySFlight = service.modifyScheduledFlight(scheduleFlight);
        if (modifySFlight == null) {
            return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<ScheduledFlight>(modifySFlight, HttpStatus.OK);
        }
    }
    @ApiOperation("To delete scheduleFlight details by ID")
    @DeleteMapping("/delete")
    public String deleteSF(@RequestParam Long flightId) throws RecordNotFoundException {
        return service.removeScheduledFlight(flightId);
    }
    @ApiOperation("To return user details by ID")
    @GetMapping("/search")
    @ExceptionHandler(ScheduledFlightNotFoundException.class)
    public ResponseEntity<ScheduledFlight> viewSF(@RequestParam Long flightId) throws ScheduledFlightNotFoundException {
        ScheduledFlight searchSFlight = service.viewScheduledFlight(flightId);
        if (searchSFlight == null) {
            return new ResponseEntity("Flight not present", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<ScheduledFlight>(searchSFlight, HttpStatus.OK);
        }
    }
    @ApiOperation("To return all ScheduleFlight details")
    @GetMapping("/viewAll")
    public Iterable<ScheduledFlight> viewAllSF() {
        return service.getScheduledFlightRepository().findAll();
    }



}
