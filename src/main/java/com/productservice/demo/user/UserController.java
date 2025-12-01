package com.productservice.demo.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.productservice.demo.ApiResponseData;
import com.productservice.demo.TokenResponse;
import com.productservice.demo.security.BCrypt;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile")
    public ApiResponseData<String> getProfile(User user) {
        return ApiResponseData.<String>builder().data(user.getUsername()).build();
    }
    

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

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        var email = loginRequest.getEmail();
        var password = loginRequest.getPassword();

        var user = userRepository.findFirstByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        var token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setTokenExpiredAt(next30Day());
        userRepository.save(user);

        return TokenResponse.builder().token(token).build();
    }

    private Long next30Day() {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    }
}
