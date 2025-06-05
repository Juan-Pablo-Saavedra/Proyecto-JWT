package com.example.JWT.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.JWT.DTO.usersDTO;
import com.example.JWT.service.usersService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/users")
public class usersController {

    @Autowired
    private usersService usersService;

    // Registrar un nuevo usuario con validaciones
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody usersDTO usuarioDTO) {
        String response = usersService.save(usuarioDTO);
        HttpStatus status = response.equals("Usuario guardado exitosamente.")
                ? HttpStatus.CREATED
                : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }
    
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<usersDTO>> getAllUsers() {
        List<usersDTO> usuarios = usersService.getAllUsers();
        return (usuarios == null || usuarios.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    // Obtener un usuario por ID con manejo de errores
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        Optional<usersDTO> usuarioOpt = usersService.getUserById(id);
        return usuarioOpt.isPresent()
                ? ResponseEntity.ok(usuarioOpt.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
    }
    
    // Actualizar un usuario con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody usersDTO usuarioDTO) {
        String response = usersService.updateUser(id, usuarioDTO);
        HttpStatus status = response.equals("Usuario actualizado exitosamente.")
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }
    
    // Eliminar un usuario con control de errores
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        String response = usersService.deleteUser(id);
        HttpStatus status = response.equals("Usuario eliminado exitosamente.")
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}
