package com.soluctions.attos.domus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soluctions.attos.domus.dtos.UserDto;
import com.soluctions.attos.domus.entities.User;
import com.soluctions.attos.domus.services.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/attos-api")
public class UsersController {
    private final UserService userService;

    @GetMapping("/find-users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> listUsers = new ArrayList<>();

        try {
            listUsers = userService.listAllUsers();

            return ResponseEntity.ok(listUsers);

        } catch (Exception e) {
            log.error("Error in controller, find all users: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/new-user")
    public ResponseEntity<Void> createNewUser(@RequestBody UserDto userDto) {
        try {
            userService.newUser(userDto);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error in controller, new user: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/update-user")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto) {
        try {
            userService.updateUser(userDto);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error in controller, update user: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/inactive-user/{id}")
    public ResponseEntity<Void> inactiveUser(@PathVariable("id") Long id) {
        try {
            userService.inactiveUser(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            log.error("Error in controller, inactive user: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
