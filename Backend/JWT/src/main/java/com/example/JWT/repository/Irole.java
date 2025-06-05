package com.example.JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.JWT.model.role;
import java.util.Optional;

@Repository
public interface Irole extends JpaRepository<role, Integer> {

    // Búsqueda de role por nombre exacto
    Optional<role> findByName(String name);
}
