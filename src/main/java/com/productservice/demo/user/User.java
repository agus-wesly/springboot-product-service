package com.productservice.demo.user;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTokenExpiredAt() {
        return tokenExpiredAt;
    }

    public void setTokenExpiredAt(Long tokenExpiredAt) {
        this.tokenExpiredAt = tokenExpiredAt;
    }
}
