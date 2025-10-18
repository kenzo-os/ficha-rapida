package com.devficha.ficharapida.infrastructure.repository;

import com.devficha.ficharapida.business.entities.Socorrista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocorristaRepository  extends JpaRepository<Socorrista, Long> {
    Optional<Socorrista> findById(Long id);

    boolean existsByMatricula(String matricula);
}
