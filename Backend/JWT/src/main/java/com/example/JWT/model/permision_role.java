package com.example.JWT.model;

import jakarta.persistence.*;

@Entity
@Table(name = "permision_role")
public class permision_role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    // Relación con role
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private role role;
    
    // Relación con page
    @ManyToOne(optional = false)
    @JoinColumn(name = "page_id", nullable = false)
    private page page;
    
    public permision_role() {
    }
    
    public permision_role(role role, page page) {
        this.role = role;
        this.page = page;
    }
    
    public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }
  
    public role getRole() {
        return role;
    }
  
    public void setRole(role role) {
        this.role = role;
    }
  
    public page getPage() {
        return page;
    }
  
    public void setPage(page page) {
        this.page = page;
    }
}
