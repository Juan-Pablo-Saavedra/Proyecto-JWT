package com.example.JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.JWT.model.page;
import java.util.List;

@Repository
public interface Ipage extends JpaRepository<page, Integer> {

    // Consulta que permite filtrar páginas por nombre (búsqueda parcial)
    List<page> findByNameContaining(String name);
}
