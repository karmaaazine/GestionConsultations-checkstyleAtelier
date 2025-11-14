package com.gestionconsultations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionconsultations.entity.Medicament;

/**
 * Repository pour l'entité Medicament.
 */
@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
}

