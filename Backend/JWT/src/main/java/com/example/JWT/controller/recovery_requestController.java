package com.example.JWT.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.JWT.DTO.recovery_requestDTO;
import com.example.JWT.service.recovery_requestService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/recovery-requests")
public class recovery_requestController {

    @Autowired
    private recovery_requestService recoveryRequestService;

    // Registrar una nueva solicitud de recuperación con validaciones
    @PostMapping
    public ResponseEntity<String> registerRecoveryRequest(@RequestBody recovery_requestDTO dto) {
        String response = recoveryRequestService.save(dto);
        HttpStatus status = response.equals("Solicitud de recuperación guardada exitosamente.")
                ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Obtener todas las solicitudes de recuperación
    @GetMapping
    public ResponseEntity<List<recovery_requestDTO>> getAllRecoveryRequests() {
        List<recovery_requestDTO> lista = recoveryRequestService.getAllRecoveryRequests();
        return (lista == null || lista.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Obtener una solicitud de recuperación por ID con manejo de errores
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecoveryRequestById(@PathVariable int id) {
        Optional<recovery_requestDTO> dtoOpt = recoveryRequestService.getRecoveryRequestById(id);
        return dtoOpt.isPresent()
                ? ResponseEntity.ok(dtoOpt.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Solicitud de recuperación no encontrada.");
    }

    // Actualizar una solicitud de recuperación con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecoveryRequest(@PathVariable int id, @RequestBody recovery_requestDTO dto) {
        String response = recoveryRequestService.updateRecoveryRequest(id, dto);
        HttpStatus status = response.equals("Solicitud de recuperación actualizada exitosamente.")
                ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Eliminar una solicitud de recuperación con control de errores
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecoveryRequest(@PathVariable int id) {
        String response = recoveryRequestService.deleteRecoveryRequest(id);
        HttpStatus status = response.equals("Solicitud de recuperación eliminada exitosamente.")
                ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}
