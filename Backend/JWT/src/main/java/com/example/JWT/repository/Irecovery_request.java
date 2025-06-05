package com.example.JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.JWT.model.recovery_request;
import java.util.List;

@Repository
public interface Irecovery_request extends JpaRepository<recovery_request, Integer> {
    
    // Filtra las solicitudes de recuperación pertenecientes a un usuario (por el id del usuario)
    List<recovery_request> findByUser_Id(int userId);
    
    // Busca una solicitud de recuperación por token
    recovery_request findByToken(String token);
}
