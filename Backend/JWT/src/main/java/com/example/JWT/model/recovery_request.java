package com.example.JWT.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recovery_request")
public class recovery_request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "token", nullable = false)
    private String token;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    
    // Cada solicitud de recuperación pertenece a un usuario (users)
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private users user;
    
    public recovery_request() {
    }
    
    public recovery_request(String token, LocalDateTime createdAt, LocalDateTime expiresAt, users user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
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
  
    public users getUser() {
        return user;
    }
  
    public void setUser(users user) {
        this.user = user;
    }
}
