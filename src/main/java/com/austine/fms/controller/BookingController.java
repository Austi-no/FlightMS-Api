package com.austine.fms.controller;

import com.austine.fms.MessageUtil.ApiResponse;
import com.austine.fms.MessageUtil.CustomMessages;
import com.austine.fms.exceptions.RecordAlreadyPresentException;
import com.austine.fms.exceptions.RecordNotFoundException;
import com.austine.fms.model.Airport;
import com.austine.fms.model.Booking;
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
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private ApiService service;

    @ApiOperation("To save a Booking details")
    @ExceptionHandler(RecordNotFoundException.class)
    @PostMapping(value = "/addBooking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody Booking booking){
        Optional<Booking> findById = service.getBookingRepsitory().findById(booking.getBookingId());
        try {
            if (!findById.isPresent()) {
                Booking saveBooking = service.getBookingRepsitory().save(booking);
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, saveBooking));
            }
            else throw new RecordAlreadyPresentException("Booking with code: " + booking.getBookingId() + "already present");
        }
        catch(RecordAlreadyPresentException e) {
            return new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("To return all Booking details")
    @GetMapping("/allBooking")
    public List<Booking> findAllBooking(){
        return service.getBookingRepsitory().findAll();
    }

    @ApiOperation("To return Booking details by ID")
    @GetMapping("/getBooking/{bookingId}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity getBookingById(@PathVariable("bookingId") Long bookingId){
        return service.getBookingRepsitory().findById(bookingId).map(record->{
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, record));
        }).orElseThrow(()-> new RecordNotFoundException("Record Not Found for: " + bookingId));
    }

    @ApiOperation("To Update Booking details by ID")
    @PutMapping("/updateBooking")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity updateBooking(@RequestBody Booking booking) {
        Optional<Booking> findById = service.getBookingRepsitory().findById(booking.getBookingId());
        if (findById.isPresent()){
            service.getBookingRepsitory().save(booking);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, booking));
        }
        else
            throw new RecordNotFoundException("Booking with code: " + booking.getBookingId()+ "not exists");


    }

    @ApiOperation("To delete booking details by ID")
    @DeleteMapping("/deleteBooking/{bookingId}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity deleteBookingById(@PathVariable ("bookingId") Long bookingId){
        return service.getBookingRepsitory().findById(bookingId).map(record->{
            service.getBookingRepsitory().deleteById(bookingId);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, CustomMessages.DeletedMessage));
        }).orElseThrow(()-> new RecordNotFoundException("Record Not Found for: " + bookingId));
    }


}
