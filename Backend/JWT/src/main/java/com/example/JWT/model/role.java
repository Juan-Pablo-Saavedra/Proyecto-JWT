package com.example.JWT.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    // Un role se asigna a muchos users
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<users> users = new ArrayList<>();
    
    // Un role tiene muchos registros en permision_role
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<permision_role> permisionRoles = new ArrayList<>();

    public role() {
    }

    public role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }
  
    public String getName() { 
        return name; 
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public String getDescription() {
        return description;
    }
  
    public void setDescription(String description) {
        this.description = description;
    }

    public List<users> getUsers() {
        return users;
    }

    public void setUsers(List<users> users) {
        this.users = users;
    }

    public List<permision_role> getPermisionRoles() {
        return permisionRoles;
    }

    public void setPermisionRoles(List<permision_role> permisionRoles) {
        this.permisionRoles = permisionRoles;
    }
}
