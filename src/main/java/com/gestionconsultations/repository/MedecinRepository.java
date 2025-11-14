package com.gestionconsultations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionconsultations.entity.Medecin;

/**
 * Repository pour l'entité Medecin.
 */
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}

