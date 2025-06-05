package com.example.JWT.service;

import com.example.JWT.DTO.usersDTO;
import com.example.JWT.model.users;
import com.example.JWT.model.role;
import com.example.JWT.repository.Iusers;
import com.example.JWT.repository.Irole;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usersService {

    @Autowired
    private Iusers usersRepository;

    @Autowired
    private Irole roleRepository;
    
    // Método para guardar un usuario con validaciones
    public String save(usersDTO usuarioDTO) {
        // Validaciones
        if (usuarioDTO.getUsername() == null || usuarioDTO.getUsername().trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }
        if (usuarioDTO.getEmail() == null || usuarioDTO.getEmail().trim().isEmpty()) {
            return "El correo electrónico es obligatorio.";
        }
        if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().isEmpty()) {
            return "La contraseña es obligatoria.";
        }
        if (usuarioDTO.getRoleId() <= 0) {
            return "El identificador de rol es inválido.";
        }
        
        Optional<role> roleOpt = roleRepository.findById(usuarioDTO.getRoleId());
        if (roleOpt.isEmpty()) {
            return "El identificador de rol no es válido.";
        }
        
        try {
            users usuarioEntity = convertToEntity(usuarioDTO, roleOpt.get());
            usersRepository.save(usuarioEntity);
            return "Usuario guardado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
            return "Error al guardar el usuario.";
        }
    }
    
    // Método para obtener todos los usuarios
    public List<usersDTO> getAllUsers() {
        try {
            List<users> lista = usersRepository.findAll();
            return lista.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener los usuarios: " + e.getMessage());
            return null;
        }
    }
    
    // Método para obtener un usuario por ID
    public Optional<usersDTO> getUserById(int id) {
        Optional<users> usuarioOpt = usersRepository.findById(id);
        return usuarioOpt.map(this::convertToDTO);
    }
    
    // Método para actualizar un usuario con validaciones
    public String updateUser(int id, usersDTO usuarioDTO) {
        Optional<users> existingOpt = usersRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return "Usuario no encontrado.";
        }
        if (usuarioDTO.getUsername() == null || usuarioDTO.getUsername().trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }
        if (usuarioDTO.getEmail() == null || usuarioDTO.getEmail().trim().isEmpty()) {
            return "El correo electrónico es obligatorio.";
        }
        if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().isEmpty()) {
            return "La contraseña es obligatoria.";
        }
        if (usuarioDTO.getRoleId() <= 0) {
            return "El identificador de rol es inválido.";
        }
        
        Optional<role> roleOpt = roleRepository.findById(usuarioDTO.getRoleId());
        if (roleOpt.isEmpty()) {
            return "El identificador de rol no es válido.";
        }
        
        try {
            users usuarioEntity = existingOpt.get();
            usuarioEntity.setUsername(usuarioDTO.getUsername());
            usuarioEntity.setEmail(usuarioDTO.getEmail());
            usuarioEntity.setPassword(usuarioDTO.getPassword());
            usuarioEntity.setRole(roleOpt.get());
            usersRepository.save(usuarioEntity);
            return "Usuario actualizado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            return "Error al actualizar el usuario.";
        }
    }
    
    // Método para eliminar un usuario
    public String deleteUser(int id) {
        Optional<users> usuarioOpt = usersRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            return "Usuario no encontrado.";
        }
        try {
            usersRepository.deleteById(id);
            return "Usuario eliminado exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
            return "Error al eliminar el usuario.";
        }
    }
    
    // Métodos de conversión entre DTO y Entidad
    private usersDTO convertToDTO(users u) {
        usersDTO dto = new usersDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
        dto.setRoleId(u.getRole().getId());
        return dto;
    }
    
    private users convertToEntity(usersDTO dto, role r) {
        users u = new users();
        u.setUsername(dto.getUsername());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setRole(r);
        return u;
    }
}
