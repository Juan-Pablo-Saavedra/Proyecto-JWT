package com.example.JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.JWT.model.users;
import java.util.Optional;
import java.util.List;

@Repository
public interface Iusers extends JpaRepository<users, Integer> {
    
    // Filtra por username (ejemplo de búsqueda exacta)
    Optional<users> findByUsername(String username);
    
    // Filtra por email que contenga la cadena (búsqueda parcial)
    List<users> findByEmailContaining(String email);
}
