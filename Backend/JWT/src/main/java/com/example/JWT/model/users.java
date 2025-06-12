package com.example.JWT.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class users implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    // Muchos users tienen un único role
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private role role;
    
    // Un usuario puede tener múltiples solicitudes de recuperación
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<recovery_request> recoveryRequests = new ArrayList<>();

    public users() {
    }

    public users(String username, String email, String password, role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public role getRole() { 
        return role; 
    }

    public void setRole(role role) { 
        this.role = role; 
    }

    public List<recovery_request> getRecoveryRequests() { 
        return recoveryRequests; 
    }

    public void setRecoveryRequests(List<recovery_request> recoveryRequests) {
        this.recoveryRequests = recoveryRequests;
    }

     @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }
}
