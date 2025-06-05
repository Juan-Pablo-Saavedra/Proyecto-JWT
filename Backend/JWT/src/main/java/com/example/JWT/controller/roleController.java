package com.example.JWT.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.JWT.DTO.roleDTO;
import com.example.JWT.service.roleService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/roles")
public class roleController {

    @Autowired
    private roleService roleService;

    // Registrar un nuevo rol con validaciones
    @PostMapping
    public ResponseEntity<String> registerRole(@RequestBody roleDTO roleDTO) {
        String response = roleService.save(roleDTO);
        HttpStatus status = response.equals("Rol guardado exitosamente.")
                ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<roleDTO>> getAllRoles() {
        List<roleDTO> roles = roleService.getAllRoles();
        return (roles == null || roles.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // Obtener rol por ID con manejo de errores
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable int id) {
        Optional<roleDTO> roleOpt = roleService.getRoleById(id);
        return roleOpt.isPresent()
                ? ResponseEntity.ok(roleOpt.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado.");
    }

    // Actualizar un rol con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRole(@PathVariable int id, @RequestBody roleDTO roleDTO) {
        String response = roleService.updateRole(id, roleDTO);
        HttpStatus status = response.equals("Rol actualizado exitosamente.")
                ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Eliminar un rol con control de errores
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable int id) {
        String response = roleService.deleteRole(id);
        HttpStatus status = response.equals("Rol eliminado exitosamente.")
                ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}
