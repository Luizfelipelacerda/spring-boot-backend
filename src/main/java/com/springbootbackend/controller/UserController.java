package com.springbootbackend.controller;

import com.springbootbackend.Model.User;
import com.springbootbackend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/user")
public class UserController {


    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(this.userService.getUser());
    }



}
