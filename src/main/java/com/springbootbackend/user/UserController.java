package com.springbootbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/user")
public class UserController {


    private final UserService userService;

    @GetMapping(path = "/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email){
        UserDTO user = this.userService.getUser(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<Void> editeUser(@RequestBody UserRequest userRequest){
        userService.updateUser(userRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
