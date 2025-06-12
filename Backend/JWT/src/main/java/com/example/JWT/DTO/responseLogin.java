package com.example.JWT.DTO;

public class responseLogin {
    
    private String token;
    private String username;
    private String email;
    
    public responseLogin() {
    }
    
    public responseLogin(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }
    
    // Getters y setters
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
