package com.bolehunt.controller;

import com.bolehunt.domain.User;
import com.bolehunt.domain.dto.SignupRequest;
import com.bolehunt.payload.ApiResponse;
import com.bolehunt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(signupRequest.getPassword());

        userService.insertUser(user);

        return ResponseEntity.ok(new ApiResponse("User registered successfully"));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable(value = "username") String username) {
        return userService.findUserByUsername(username);
    }

}
