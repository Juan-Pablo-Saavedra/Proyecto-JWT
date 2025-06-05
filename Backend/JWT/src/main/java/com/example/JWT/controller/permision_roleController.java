package com.example.JWT.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.JWT.DTO.permision_roleDTO;
import com.example.JWT.service.permision_roleService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/permision-roles")
public class permision_roleController {

    @Autowired
    private permision_roleService permisionRoleService;

    // Registrar un nuevo permiso de rol con validaciones
    @PostMapping
    public ResponseEntity<String> registerPermisionRole(@RequestBody permision_roleDTO dto) {
        String response = permisionRoleService.save(dto);
        HttpStatus status = response.equals("Permiso de rol guardado exitosamente.")
                ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Obtener todos los permisos de rol
    @GetMapping
    public ResponseEntity<List<permision_roleDTO>> getAllPermisionRoles() {
        List<permision_roleDTO> lista = permisionRoleService.getAllPermisionRoles();
        return (lista == null || lista.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Obtener un permiso de rol por ID con manejo de errores
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPermisionRoleById(@PathVariable int id) {
        Optional<permision_roleDTO> dtoOpt = permisionRoleService.getPermisionRoleById(id);
        return dtoOpt.isPresent()
                ? ResponseEntity.ok(dtoOpt.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permiso de rol no encontrado.");
    }

    // Actualizar un permiso de rol con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePermisionRole(@PathVariable int id, @RequestBody permision_roleDTO dto) {
        String response = permisionRoleService.updatePermisionRole(id, dto);
        HttpStatus status = response.equals("Permiso de rol actualizado exitosamente.")
                ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Eliminar un permiso de rol con control de errores
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermisionRole(@PathVariable int id) {
        String response = permisionRoleService.deletePermisionRole(id);
        HttpStatus status = response.equals("Permiso de rol eliminado exitosamente.")
                ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}
