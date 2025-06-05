package com.example.JWT.service;

import com.example.JWT.DTO.roleDTO;
import com.example.JWT.model.role;
import com.example.JWT.repository.Irole;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class roleService {

    @Autowired
    private Irole roleRepository;
    
    // Método para guardar un rol
    public String save(roleDTO roleDTO) {
        if (roleDTO.getName() == null || roleDTO.getName().trim().isEmpty()) {
            return "El nombre del rol es obligatorio.";
        }
        try {
            role rEntity = convertToEntity(roleDTO);
            roleRepository.save(rEntity);
            return "Rol guardado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al guardar el rol: " + e.getMessage());
            return "Error al guardar el rol.";
        }
    }
    
    // Método para obtener todos los roles
    public List<roleDTO> getAllRoles() {
        try {
            List<role> lista = roleRepository.findAll();
            return lista.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener los roles: " + e.getMessage());
            return null;
        }
    }
    
    // Método para obtener un rol por ID
    public Optional<roleDTO> getRoleById(int id) {
        Optional<role> roleOpt = roleRepository.findById(id);
        return roleOpt.map(this::convertToDTO);
    }
    
    // Método para actualizar un rol
    public String updateRole(int id, roleDTO roleDTO) {
        Optional<role> existingOpt = roleRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return "Rol no encontrado.";
        }
        if (roleDTO.getName() == null || roleDTO.getName().trim().isEmpty()) {
            return "El nombre del rol es obligatorio.";
        }
        try {
            role rEntity = existingOpt.get();
            rEntity.setName(roleDTO.getName());
            rEntity.setDescription(roleDTO.getDescription());
            roleRepository.save(rEntity);
            return "Rol actualizado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al actualizar el rol: " + e.getMessage());
            return "Error al actualizar el rol.";
        }
    }
    
    // Método para eliminar un rol
    public String deleteRole(int id) {
        Optional<role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isEmpty()) {
            return "Rol no encontrado.";
        }
        try {
            roleRepository.deleteById(id);
            return "Rol eliminado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al eliminar el rol: " + e.getMessage());
            return "Error al eliminar el rol.";
        }
    }
    
    // Métodos de conversión
    private roleDTO convertToDTO(role r) {
        roleDTO dto = new roleDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setDescription(r.getDescription());
        return dto;
    }
    
    private role convertToEntity(roleDTO dto) {
        role r = new role();
        r.setName(dto.getName());
        r.setDescription(dto.getDescription());
        return r;
    }
}
