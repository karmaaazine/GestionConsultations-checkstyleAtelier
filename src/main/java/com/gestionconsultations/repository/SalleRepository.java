package com.gestionconsultations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionconsultations.entity.Salle;

/**
 * Repository pour l'entité Salle.
 */
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
}

