package com.example.JWT.service;

import com.example.JWT.DTO.permision_roleDTO;
import com.example.JWT.model.permision_role;
import com.example.JWT.model.role;
import com.example.JWT.model.page;
import com.example.JWT.repository.Ipermision_role;
import com.example.JWT.repository.Irole;
import com.example.JWT.repository.Ipage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class permision_roleService {

    @Autowired
    private Ipermision_role permisionRoleRepository;
    
    @Autowired
    private Irole roleRepository;
    
    @Autowired
    private Ipage pageRepository;
    
    // Método para guardar un permiso de rol
    public String save(permision_roleDTO dto) {
        if (dto.getRoleId() <= 0 || dto.getPageId() <= 0) {
            return "Los identificadores de rol y página deben ser válidos.";
        }
        Optional<role> roleOpt = roleRepository.findById(dto.getRoleId());
        Optional<page> pageOpt = pageRepository.findById(dto.getPageId());
        if (roleOpt.isEmpty() || pageOpt.isEmpty()) {
            return "El identificador de rol o de página no es válido.";
        }
        try {
            permision_role prEntity = convertToEntity(dto, roleOpt.get(), pageOpt.get());
            permisionRoleRepository.save(prEntity);
            return "Permiso de rol guardado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al guardar el permiso de rol: " + e.getMessage());
            return "Error al guardar el permiso de rol.";
        }
    }
    
    // Método para obtener todos los permisos de rol
    public List<permision_roleDTO> getAllPermisionRoles() {
        try {
            List<permision_role> lista = permisionRoleRepository.findAll();
            return lista.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener los permisos de rol: " + e.getMessage());
            return null;
        }
    }
    
    // Método para obtener un permiso de rol por ID
    public Optional<permision_roleDTO> getPermisionRoleById(int id) {
        Optional<permision_role> prOpt = permisionRoleRepository.findById(id);
        return prOpt.map(this::convertToDTO);
    }
    
    // Método para actualizar un permiso de rol
    public String updatePermisionRole(int id, permision_roleDTO dto) {
        Optional<permision_role> existingOpt = permisionRoleRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return "Permiso de rol no encontrado.";
        }
        if (dto.getRoleId() <= 0 || dto.getPageId() <= 0) {
            return "Los identificadores de rol y página deben ser válidos.";
        }
        Optional<role> roleOpt = roleRepository.findById(dto.getRoleId());
        Optional<page> pageOpt = pageRepository.findById(dto.getPageId());
        if (roleOpt.isEmpty() || pageOpt.isEmpty()) {
            return "El identificador de rol o de página no es válido.";
        }
        try {
            permision_role prEntity = existingOpt.get();
            prEntity.setRole(roleOpt.get());
            prEntity.setPage(pageOpt.get());
            permisionRoleRepository.save(prEntity);
            return "Permiso de rol actualizado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al actualizar el permiso de rol: " + e.getMessage());
            return "Error al actualizar el permiso de rol.";
        }
    }
    
    // Método para eliminar un permiso de rol
    public String deletePermisionRole(int id) {
        Optional<permision_role> prOpt = permisionRoleRepository.findById(id);
        if (prOpt.isEmpty()) {
            return "Permiso de rol no encontrado.";
        }
        try {
            permisionRoleRepository.deleteById(id);
            return "Permiso de rol eliminado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al eliminar el permiso de rol: " + e.getMessage());
            return "Error al eliminar el permiso de rol.";
        }
    }
    
    // Métodos de conversión
    private permision_roleDTO convertToDTO(permision_role pr) {
        permision_roleDTO dto = new permision_roleDTO();
        dto.setId(pr.getId());
        dto.setRoleId(pr.getRole().getId());
        dto.setPageId(pr.getPage().getId());
        return dto;
    }
    
    private permision_role convertToEntity(permision_roleDTO dto, role r, page p) {
        return new permision_role(r, p);
    }
}
