package com.example.JWT.DTO;

public class responsesDTO {
    
    private int status;
    private String message;
    private Object data; // Puede contener cualquier dato, como un objeto o una lista
    
    public responsesDTO() {
    }
    
    public responsesDTO(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    // Getters y setters
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}
