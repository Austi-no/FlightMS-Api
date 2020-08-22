package com.austine.fms.controller;

import com.austine.fms.MessageUtil.ApiResponse;
import com.austine.fms.MessageUtil.CustomMessages;
import com.austine.fms.exceptions.RecordAlreadyPresentException;
import com.austine.fms.exceptions.RecordNotFoundException;
import com.austine.fms.model.Flight;
import com.austine.fms.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private ApiService service;

    @ApiOperation("To save a Flight details")
    @ExceptionHandler(RecordNotFoundException.class)
    @PostMapping(value = "/addFlight", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFlight(@RequestBody Flight flight){
        Optional<Flight> findById = service.getFlightRepository().findById(flight.getFlightId());
        try {
            if (!findById.isPresent()) {
                Flight saveFlight = service.getFlightRepository().save(flight);
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, saveFlight));
            }
            else throw new RecordAlreadyPresentException("Flight with code: " + flight.getFlightId() + "already present");
        }
        catch(RecordAlreadyPresentException e) {
            return new ResponseEntity<Flight>(flight, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("To return all Flight details")
    @GetMapping("/allFlights")
    public List<Flight> findAllFlights(){
        return service.getFlightRepository().findAll();
    }

    @ApiOperation("To return Flight details by ID")
    @GetMapping("/getFlights/{flightId}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity getFlightById(@PathVariable("flightId") Long flightId){
        return service.getFlightRepository().findById(flightId).map(record->{
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, record));
        }).orElseThrow(()-> new RecordNotFoundException("Record Not Found for: " + flightId));
    }

    @ApiOperation("To Update Flight details by ID")
    @PutMapping("/updateFlight")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity updateFlight(@RequestBody Flight flight) {
        Optional<Flight> findById = service.getFlightRepository().findById(flight.getFlightId());
        if (findById.isPresent()){
            service.getFlightRepository().save(flight);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, flight));
        }
        else
            throw new RecordNotFoundException("Booking with code: " + flight.getFlightId()+ "not exists");


    }

    @ApiOperation("To delete flight details by ID")
    @DeleteMapping("/deleteFlight/{flightId}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity deleteFlightById(@PathVariable ("flightId") Long flightId){
        return service.getFlightRepository().findById(flightId).map(record->{
            service.getFlightRepository().deleteById(flightId);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, CustomMessages.DeletedMessage));
        }).orElseThrow(()-> new RecordNotFoundException("Record Not Found for: " + flightId));
    }


}
