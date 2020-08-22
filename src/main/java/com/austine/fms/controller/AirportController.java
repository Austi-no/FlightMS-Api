package com.austine.fms.controller;

import com.austine.fms.MessageUtil.ApiResponse;
import com.austine.fms.MessageUtil.CustomMessages;
import com.austine.fms.exceptions.RecordAlreadyPresentException;
import com.austine.fms.exceptions.RecordNotFoundException;
import com.austine.fms.model.Airport;
import com.austine.fms.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    private ApiService service;


    @ApiOperation("To save a Airport details")
    @ExceptionHandler(RecordNotFoundException.class)
    @PostMapping(value = "/addAirport", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addAirport(@RequestBody Airport airport){
        Optional<Airport> findById = service.getAirportRepository().findById(airport.getAirportCode());
        try {
            if (!findById.isPresent()) {
                Airport saveAirport = service.getAirportRepository().save(airport);
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, saveAirport));
            }
            else throw new RecordAlreadyPresentException("Airport with code: " + airport.getAirportCode() + "already present");
        }
        catch(RecordAlreadyPresentException e) {
            return new ResponseEntity<Airport>(airport, HttpStatus.NOT_FOUND);
        }

       }

    @ApiOperation("To return all airport details")
    @GetMapping("/allAirport")
    public List<Airport> findAllAirport(){
        return service.getAirportRepository().findAll();
    }

    @ApiOperation("To return airport details by ID")
    @GetMapping("/getAirport/{airportCode}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity getAirportById(@PathVariable("airportCode") String airportCode){
        return service.getAirportRepository().findById(airportCode).map(record->{
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, record));
        }).orElseThrow(()-> new RecordNotFoundException("Record Not Found for: " + airportCode));
    }


    @ApiOperation("To Update airport details by ID")
    @PutMapping("/updateAirport")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity modifyAirport(@RequestBody Airport airport) {
        Optional<Airport> findById = service.getAirportRepository().findById(airport.getAirportCode());
        if (findById.isPresent()){
            service.getAirportRepository().save(airport);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, airport));
        }
        else
            throw new RecordNotFoundException("Airport with code: " + airport.getAirportCode()+ "not exists");


    }

    @ApiOperation("To delete airport details by ID")
    @DeleteMapping("/deleteAirport/{airportCode}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity deleteCustomerAccountById(@PathVariable ("airportCode") String airportCode){
        return service.getAirportRepository().findById(airportCode).map(record->{
            service.getAirportRepository().deleteById(airportCode);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, CustomMessages.DeletedMessage));
        }).orElseThrow(()-> new RecordNotFoundException("Record Not Found for: " + airportCode));
    }










}
