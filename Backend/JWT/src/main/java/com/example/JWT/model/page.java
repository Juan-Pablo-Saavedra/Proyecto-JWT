package com.example.JWT.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "page")
public class page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "url", nullable = false)
    private String url;
    
    // Una página puede tener múltiples registros en permision_role
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<permision_role> permisionRoles = new ArrayList<>();
    
    public page() {
    }
    
    public page(String name, String url) {
        this.name = name;
        this.url = url;
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
  
    public String getUrl() {
        return url;
    }
  
    public void setUrl(String url) {
        this.url = url;
    }
  
    public List<permision_role> getPermisionRoles() {
        return permisionRoles;
    }
  
    public void setPermisionRoles(List<permision_role> permisionRoles) {
        this.permisionRoles = permisionRoles;
    }
}
