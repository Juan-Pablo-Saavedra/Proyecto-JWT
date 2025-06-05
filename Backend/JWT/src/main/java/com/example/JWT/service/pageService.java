package com.example.JWT.service;

import com.example.JWT.DTO.pageDTO;
import com.example.JWT.model.page;
import com.example.JWT.repository.Ipage;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pageService {

    @Autowired
    private Ipage pageRepository;
    
    // Método para guardar una página
    public String save(pageDTO pageDTO) {
        if (pageDTO.getName() == null || pageDTO.getName().trim().isEmpty()) {
            return "El nombre de la página es obligatorio.";
        }
        if (pageDTO.getUrl() == null || pageDTO.getUrl().trim().isEmpty()) {
            return "La URL de la página es obligatoria.";
        }
        try {
            page pEntity = convertToEntity(pageDTO);
            pageRepository.save(pEntity);
            return "Página guardada exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al guardar la página: " + e.getMessage());
            return "Error al guardar la página.";
        }
    }
    
    // Método para obtener todas las páginas
    public List<pageDTO> getAllPages() {
        try {
            List<page> lista = pageRepository.findAll();
            return lista.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error al obtener las páginas: " + e.getMessage());
            return null;
        }
    }
    
    // Método para obtener una página por ID
    public Optional<pageDTO> getPageById(int id) {
        Optional<page> pageOpt = pageRepository.findById(id);
        return pageOpt.map(this::convertToDTO);
    }
    
    // Método para actualizar una página
    public String updatePage(int id, pageDTO pageDTO) {
        Optional<page> existingOpt = pageRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return "Página no encontrada.";
        }
        if (pageDTO.getName() == null || pageDTO.getName().trim().isEmpty()) {
            return "El nombre de la página es obligatorio.";
        }
        if (pageDTO.getUrl() == null || pageDTO.getUrl().trim().isEmpty()) {
            return "La URL de la página es obligatoria.";
        }
        try {
            page pEntity = existingOpt.get();
            pEntity.setName(pageDTO.getName());
            pEntity.setUrl(pageDTO.getUrl());
            pageRepository.save(pEntity);
            return "Página actualizada exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al actualizar la página: " + e.getMessage());
            return "Error al actualizar la página.";
        }
    }
    
    // Método para eliminar una página
    public String deletePage(int id) {
        Optional<page> pageOpt = pageRepository.findById(id);
        if (pageOpt.isEmpty()) {
            return "Página no encontrada.";
        }
        try {
            pageRepository.deleteById(id);
            return "Página eliminada exitosamente.";
        } catch (Exception e) {
            System.err.println("Error al eliminar la página: " + e.getMessage());
            return "Error al eliminar la página.";
        }
    }
    
    // Métodos de conversión
    private pageDTO convertToDTO(page p) {
        pageDTO dto = new pageDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setUrl(p.getUrl());
        return dto;
    }
    
    private page convertToEntity(pageDTO dto) {
        page p = new page();
        p.setName(dto.getName());
        p.setUrl(dto.getUrl());
        return p;
    }
}
