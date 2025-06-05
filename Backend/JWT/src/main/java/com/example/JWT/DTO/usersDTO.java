package com.example.JWT.DTO;

public class usersDTO {

    private int id;
    private String username;
    private String email;
    private String password; // Considera separar DTOs para operaciones de request y response
    private int roleId;

    public usersDTO() {
    }

    public usersDTO(int id, String username, String email, String password, int roleId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
