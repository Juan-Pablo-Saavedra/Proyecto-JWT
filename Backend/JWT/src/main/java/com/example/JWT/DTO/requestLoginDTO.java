package com.example.JWT.DTO;

public class requestLoginDTO {
    
    private String email;
    private String password;
    
    public requestLoginDTO() {
    }
    
    public requestLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Getters y setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
