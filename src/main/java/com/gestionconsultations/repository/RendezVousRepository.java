package com.gestionconsultations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionconsultations.entity.RendezVous;

/**
 * Repository pour l'entité RendezVous.
 */
@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}

