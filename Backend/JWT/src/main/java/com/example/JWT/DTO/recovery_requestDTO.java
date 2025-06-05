package com.example.JWT.DTO;

import java.time.LocalDateTime;

public class recovery_requestDTO {

    private int id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private int userId;

    public recovery_requestDTO() {
    }

    public recovery_requestDTO(int id, String token, LocalDateTime createdAt, LocalDateTime expiresAt, int userId) {
        this.id = id;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
