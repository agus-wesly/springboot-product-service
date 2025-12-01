package com.productservice.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productservice.demo.ApiResponseData;
import com.productservice.demo.security.BCrypt;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ApiResponseData<String> register(@Valid @RequestBody RegisterRequest registerRequest) {

        var s = new User();
        s.setEmail(registerRequest.getEmail());
        s.setUsername(registerRequest.getUsername());
        var password = BCrypt.hashpw(registerRequest.getPassword(), BCrypt.gensalt());
        s.setPassword(password);
        userRepository.save(s);

        return ApiResponseData.<String>builder().data("Successfully created new user").build();
    }
}
