package com.example.JWT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.JWT.model.permision_role;
import java.util.List;

@Repository
public interface Ipermision_role extends JpaRepository<permision_role, Integer> {
    
    // Filtra por el id del role asociado
    List<permision_role> findByRole_Id(int roleId);
    
    // Filtra por el id de la page asociada
    List<permision_role> findByPage_Id(int pageId);
}
