package com.example.JWT.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.JWT.DTO.pageDTO;
import com.example.JWT.service.pageService;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/pages")
public class pageController {

    @Autowired
    private pageService pageService;

    // Registrar una nueva página con validaciones
    @PostMapping
    public ResponseEntity<String> registerPage(@RequestBody pageDTO pageDTO) {
        String response = pageService.save(pageDTO);
        HttpStatus status = response.equals("Página guardada exitosamente.")
                ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Obtener todas las páginas
    @GetMapping
    public ResponseEntity<List<pageDTO>> getAllPages() {
        List<pageDTO> pages = pageService.getAllPages();
        return (pages == null || pages.isEmpty())
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pages, HttpStatus.OK);
    }

    // Obtener página por ID con manejo de errores
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPageById(@PathVariable int id) {
        Optional<pageDTO> pageOpt = pageService.getPageById(id);
        return pageOpt.isPresent()
                ? ResponseEntity.ok(pageOpt.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Página no encontrada.");
    }

    // Actualizar una página con validaciones
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePage(@PathVariable int id, @RequestBody pageDTO pageDTO) {
        String response = pageService.updatePage(id, pageDTO);
        HttpStatus status = response.equals("Página actualizada exitosamente.")
                ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    // Eliminar una página con control de errores
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePage(@PathVariable int id) {
        String response = pageService.deletePage(id);
        HttpStatus status = response.equals("Página eliminada exitosamente.")
                ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}
