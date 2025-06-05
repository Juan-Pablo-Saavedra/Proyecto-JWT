package com.example.JWT.service;

import com.example.JWT.DTO.recovery_requestDTO;
import com.example.JWT.model.recovery_request;
import com.example.JWT.model.users;
import com.example.JWT.repository.Irecovery_request;
import com.example.JWT.repository.Iusers;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class recovery_requestService {

    @Autowired
    private Irecovery_request recoveryRequestRepository;
    
    @Autowired
    private Iusers usersRepository;
    
    // Método para guardar una solicitud de recuperación
    public String save(recovery_requestDTO dto) {
        if (dto.getToken() == null || dto.getToken().trim().isEmpty()) {
            return "El token es obligatorio.";
        }
        if (dto.getUserId() <= 0) {
            return "El identificador de usuario es inválido.";
        }
        
        Optional<users> userOpt = usersRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) {
            return "El identificador de usuario no es válido.";
        }
        
        try {
            recovery_request rrEntity = convertToEntity(dto, userOpt.get());
            recoveryRequestRepository.save(rrEntity);
            return "Solicitud de recuperación guardada exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al guardar la solicitud de recuperación: " + e.getMessage());
            return "Error al guardar la solicitud de recuperación.";
        }
    }
    
    // Método para obtener todas las solicitudes de recuperación
    public List<recovery_requestDTO> getAllRecoveryRequests() {
        try {
            List<recovery_request> lista = recoveryRequestRepository.findAll();
            return lista.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener las solicitudes de recuperación: " + e.getMessage());
            return null;
        }
    }
    
    // Método para obtener una solicitud de recuperación por ID
    public Optional<recovery_requestDTO> getRecoveryRequestById(int id) {
        Optional<recovery_request> rrOpt = recoveryRequestRepository.findById(id);
        return rrOpt.map(this::convertToDTO);
    }
    
    // Método para actualizar una solicitud de recuperación
    public String updateRecoveryRequest(int id, recovery_requestDTO dto) {
        Optional<recovery_request> existingOpt = recoveryRequestRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return "Solicitud de recuperación no encontrada.";
        }
        if (dto.getToken() == null || dto.getToken().trim().isEmpty()) {
            return "El token es obligatorio.";
        }
        if (dto.getUserId() <= 0) {
            return "El identificador de usuario es inválido.";
        }
        
        Optional<users> userOpt = usersRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) {
            return "El identificador de usuario no es válido.";
        }
        
        try {
            recovery_request rrEntity = existingOpt.get();
            rrEntity.setToken(dto.getToken());
            rrEntity.setCreatedAt(dto.getCreatedAt());
            rrEntity.setExpiresAt(dto.getExpiresAt());
            rrEntity.setUser(userOpt.get());
            recoveryRequestRepository.save(rrEntity);
            return "Solicitud de recuperación actualizada exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al actualizar la solicitud de recuperación: " + e.getMessage());
            return "Error al actualizar la solicitud de recuperación.";
        }
    }
    
    // Método para eliminar una solicitud de recuperación
    public String deleteRecoveryRequest(int id) {
        Optional<recovery_request> rrOpt = recoveryRequestRepository.findById(id);
        if (rrOpt.isEmpty()) {
            return "Solicitud de recuperación no encontrada.";
        }
        try {
            recoveryRequestRepository.deleteById(id);
            return "Solicitud de recuperación eliminada exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al eliminar la solicitud de recuperación: " + e.getMessage());
            return "Error al eliminar la solicitud de recuperación.";
        }
    }
    
    // Métodos de conversión
    private recovery_requestDTO convertToDTO(recovery_request rr) {
        recovery_requestDTO dto = new recovery_requestDTO();
        dto.setId(rr.getId());
        dto.setToken(rr.getToken());
        dto.setCreatedAt(rr.getCreatedAt());
        dto.setExpiresAt(rr.getExpiresAt());
        dto.setUserId(rr.getUser().getId());
        return dto;
    }
    
    private recovery_request convertToEntity(recovery_requestDTO dto, users u) {
        recovery_request rr = new recovery_request();
        rr.setToken(dto.getToken());
        rr.setCreatedAt(dto.getCreatedAt());
        rr.setExpiresAt(dto.getExpiresAt());
        rr.setUser(u);
        return rr;
    }
}
