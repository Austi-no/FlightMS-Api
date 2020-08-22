package com.austine.fms.controller;

import com.austine.fms.MessageUtil.ApiResponse;
import com.austine.fms.MessageUtil.CustomMessages;
import com.austine.fms.exceptions.RecordAlreadyPresentException;
import com.austine.fms.exceptions.RecordNotFoundException;
import com.austine.fms.model.User;
import com.austine.fms.service.ApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApiService service;
    @ApiOperation("To save a User details")
    @PostMapping("/createUser")
    @ExceptionHandler(RecordAlreadyPresentException.class)
    public ResponseEntity addUser(@RequestBody User newUser) {

        Optional<User> findUserById = service.getUserRepository().findById(newUser.getUserId());
        try {
            if (!findUserById.isPresent()) {
            User saveUser =service.getUserRepository().save(newUser);
                return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, saveUser));
            }
            else
                throw new RecordAlreadyPresentException(
                        "User with Id: " + newUser.getUserId() + " already exists!!");
        }
        catch (RecordAlreadyPresentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation("To return all user details")
    @GetMapping("/AllUsers")
    public List<User> readAllUsers() {
        return service.getUserRepository().findAll();
    }

    @ApiOperation("To Update User details by ID")
    @PutMapping("/updateUser")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity updateUser(@RequestBody User updateUser) {
        Optional<User> findUserById = service.getUserRepository().findById(updateUser.getUserId());
        if (findUserById.isPresent()) {
            service.getUserRepository().save(updateUser);
        } else
            throw new RecordNotFoundException(
                    "User with Id: " + updateUser.getUserId() + " not exists!!");
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, updateUser));
    }

    @ApiOperation("To return user details by ID")
    @GetMapping("/searchUser/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> searchUserByID(@PathVariable("id") Long userId) {
        Optional<User> findById = service.getUserRepository().findById(userId);
        try {
            if (findById.isPresent()) {
                User findUser = findById.get();
                return new ResponseEntity<User>(findUser, HttpStatus.OK);
            } else
                throw new RecordNotFoundException("No record found with ID " + userId);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation("To delete user details by ID")
    @DeleteMapping("/deleteUser/{id}")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity deleteBookingByID(@PathVariable("id") Long userId) {

        Optional<User> findBookingById = service.getUserRepository().findById(userId);
        if (findBookingById.isPresent()) {
            service.getUserRepository().deleteById(userId);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Deleted, CustomMessages.DeletedMessage));

        } else
            throw new RecordNotFoundException("User not found for the entered UserID");
    }

}
