package com.example.JWT.DTO;

public class responseRegisterUserDTO {

    private String message;
    private int userId;
    private String username;
    private String email;
    
    public responseRegisterUserDTO() {
    }
    
    public responseRegisterUserDTO(String message, int userId, String username, String email) {
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
    
    // Getters y setters
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
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
